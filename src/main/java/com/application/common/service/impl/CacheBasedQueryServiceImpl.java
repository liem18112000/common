package com.application.common.service.impl;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import com.application.common.mapper.Mapper;
import com.application.common.repository.BaseRepository;
import com.application.common.service.CacheBasedQueryService;
import com.application.common.service.CacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Cache based query service.
 *
 * @param <ID>     the type parameter
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 * @param <MAPPER> the type parameter
 * @param <REPO>   the type parameter
 */
@Slf4j
public class CacheBasedQueryServiceImpl<
    ID extends Serializable,
    ENTITY extends BaseEntity<ID>,
    DTO extends BaseDto<ID>,
    MAPPER extends Mapper<ENTITY, DTO, ID>,
    REPO extends BaseRepository<ENTITY, ID>
    > extends BaseQueryService<ID, ENTITY, DTO, MAPPER, REPO>
    implements CacheBasedQueryService<ID, ENTITY, DTO> {

  /**
   * The Cache service.
   */
  protected final CacheService<String, String> cacheService;

  /**
   * The Object mapper.
   */
  protected final ObjectMapper objectMapper;

  /**
   * Instantiates a new Cache based query service.
   *
   * @param repo         the repo
   * @param mapper       the mapper
   * @param cacheService the cache service
   * @param objectMapper the object mapper
   */
  public CacheBasedQueryServiceImpl(REPO repo, MAPPER mapper,
      CacheService<String, String> cacheService,
      ObjectMapper objectMapper) {
    super(repo, mapper);
    this.cacheService = cacheService;
    this.objectMapper = objectMapper;
  }

  /**
   * Gets active user by username with exception.
   *
   * @param <KEY>                   the type parameter
   * @param <VALUE>                 the type parameter
   * @param key                     the username
   * @param handleWhenCacheNotFound the handle when cache not found
   * @param valueClass              the value class
   * @param duration                the duration
   * @return the active user by username with exception
   */
  public <KEY extends Serializable, VALUE> VALUE getByCached(
      final KEY key,
      final Function<KEY, VALUE> handleWhenCacheNotFound,
      final Class<VALUE> valueClass,
      final Duration duration) {
    VALUE value;
    final var cachedValue = this.cacheService.get(key.toString());
    if (cachedValue != null) {
      log.info("Obtain from cache: {}", cachedValue);
      try {
        value = this.objectMapper.readValue(cachedValue, valueClass);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        value = handleWhenCacheNotFound.apply(key);
      }
    } else {
      value = handleWhenCacheNotFound.apply(key);
      try {
        final var deserializedValue = this.objectMapper.writeValueAsString(value);
        log.info("Cache key '{}' value '{}'", key, deserializedValue);
        this.cacheService.cache(key.toString(), deserializedValue, duration);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
    return value;
  }

  /**
   * Gets all by cached.
   *
   * @param key                     the key
   * @param handleWhenCacheNotFound the handle when cache not found
   * @param typeReference           the type reference
   * @param duration                the duration
   * @return the all by cached
   */
  @Override
  public <KEY extends Serializable, VALUE> List<VALUE> getAllByCached(
      final KEY key,
      final Function<KEY, List<VALUE>> handleWhenCacheNotFound,
      final TypeReference<List<VALUE>> typeReference,
      final Duration duration) {
    List<VALUE> values;
    final var cachedValues = this.cacheService.get(key.toString());
    if (cachedValues != null) {
      log.info("Obtain from cache: {}", cachedValues);
      try {
        values = this.objectMapper.readValue(cachedValues, typeReference);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        values = handleWhenCacheNotFound.apply(key);
      }
    } else {
      values = handleWhenCacheNotFound.apply(key);
      try {
        final var deserializedValues = this.objectMapper.writeValueAsString(values);
        log.info("Cache key '{}' value '{}'", key, deserializedValues);
        this.cacheService.cache(key.toString(), deserializedValues, duration);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
    return values;
  }
}

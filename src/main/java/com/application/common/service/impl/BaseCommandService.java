package com.application.common.service.impl;

import static com.application.common.service.util.ServiceUtil.throwExceptionForNotFoundEntityById;
import static com.application.common.service.util.ServiceUtil.validateNotNull;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import com.application.common.mapper.Mapper;
import com.application.common.repository.BaseRepository;
import com.application.common.service.CommandService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.event.AfterCreateEvent;
import org.springframework.data.rest.core.event.AfterDeleteEvent;
import org.springframework.data.rest.core.event.AfterSaveEvent;
import org.springframework.data.rest.core.event.BeforeCreateEvent;
import org.springframework.data.rest.core.event.BeforeDeleteEvent;
import org.springframework.data.rest.core.event.BeforeSaveEvent;

/**
 * The type Base command service.
 *
 * @param <ID>     the type parameter
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 * @param <MAPPER> the type parameter
 * @param <REPO>   the type parameter
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BaseCommandService<
    ID extends Serializable,
    ENTITY extends BaseEntity<ID>,
    DTO extends BaseDto<ID>,
    MAPPER extends Mapper<ENTITY, DTO, ID>,
    REPO extends BaseRepository<ENTITY, ID>
    > implements CommandService<ID, DTO> {

  /**
   * The Repo.
   */
  protected final REPO repo;

  /**
   * The Mapper.
   */
  protected final MAPPER mapper;

  /**
   * The Publisher.
   */
  protected final ApplicationEventPublisher publisher;

  /**
   * Create dto.
   *
   * @param dto the dto
   * @return the dto
   */
  @Override
  public DTO create(DTO dto) {
    validateNotNull(dto, "DTO");
    final var entityToCreate = this.mapper.mapToEntity(dto);
    publisher.publishEvent(new BeforeCreateEvent(entityToCreate));
    final var createdEntity = this.repo.save(entityToCreate);
    publisher.publishEvent(new AfterCreateEvent(createdEntity));
    return this.mapper.mapToDto(createdEntity);
  }

  /**
   * Update dto.
   *
   * @param id  the id
   * @param dto the dto
   * @return the dto
   */
  @Override
  public DTO update(ID id, DTO dto) {
    validateNotNull(id, "ID");
    validateNotNull(dto, "DTO");
    final var entityToUpdate = this.repo.findById(id)
        .orElseThrow(throwExceptionForNotFoundEntityById(id))
        .<ENTITY, DTO>updateBasicInformation(dto);
    publisher.publishEvent(new BeforeSaveEvent(entityToUpdate));
    final var updatedEntity = this.repo.save(entityToUpdate);
    publisher.publishEvent(new AfterSaveEvent(updatedEntity));
    return this.mapper.mapToDto(updatedEntity);
  }

  /**
   * Delete dto.
   *
   * @param id the id
   * @return the dto
   */
  @Override
  public DTO delete(ID id) {
    validateNotNull(id, "ID");
    final var deleted = this.repo.findById(id)
        .orElseThrow(throwExceptionForNotFoundEntityById(id));
    publisher.publishEvent(new BeforeDeleteEvent(deleted));
    this.repo.delete(deleted);
    publisher.publishEvent(new AfterDeleteEvent(deleted));
    return this.mapper.mapToDto(deleted);
  }

}

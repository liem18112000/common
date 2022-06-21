package com.application.common.mapper;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import java.io.Serializable;

/**
 * The interface Mapper.
 *
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 * @param <ID>     the type parameter
 */
public interface Mapper<
    ENTITY extends BaseEntity<ID>,
    DTO extends BaseDto<ID>,
    ID extends Serializable> {

  /**
   * Map to dto dto.
   *
   * @param entity the entity
   * @return the dto
   */
  DTO mapToDto(ENTITY entity);

  /**
   * Map to entity entity.
   *
   * @param dto the dto
   * @return the entity
   */
  ENTITY mapToEntity(DTO dto);
}

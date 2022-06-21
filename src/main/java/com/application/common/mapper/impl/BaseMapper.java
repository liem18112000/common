package com.application.common.mapper.impl;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import com.application.common.mapper.Mapper;
import java.io.Serializable;
import org.springframework.stereotype.Component;

/**
 * The type Base mapper.
 *
 * @param <ID> the type parameter
 */
@Component
public class BaseMapper<ID extends Serializable>
    implements Mapper<BaseEntity<ID>, BaseDto<ID>, ID>, Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -5625945222617974478L;

  /**
   * Map to dto base dto.
   *
   * @param entity the entity
   * @return the base dto
   */
  @Override
  public BaseDto<ID> mapToDto(final BaseEntity<ID> entity) {
    return BaseDto.<ID>builder()
        .id(entity.getId())
        .name(entity.getName())
        .description(entity.getDescription())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
  }

  /**
   * Map to entity base entity.
   *
   * @param dto the dto
   * @return the base entity
   */
  @Override
  public BaseEntity<ID> mapToEntity(final BaseDto<ID> dto) {
    var entity = new BaseEntity<ID>();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    return entity;
  }
}

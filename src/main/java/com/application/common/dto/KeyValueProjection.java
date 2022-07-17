package com.application.common.dto;

import com.application.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * The interface Key value projection.
 */
@Projection(name = "keyValue", types = {BaseEntity.class})
public interface KeyValueProjection {

  /**
   * Gets key.
   *
   * @return the key
   */
  @Value("#{target.name}")
  Object getKey();

  /**
   * Gets value.
   *
   * @return the value
   */
  @Value("#{target.description}")
  Object getValue();
}

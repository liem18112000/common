package com.application.common.dto;

import com.application.common.entity.BaseEntity;
import org.springframework.data.rest.core.config.Projection;

/**
 * The interface Basic information projection.
 */
@Projection(name = "basic", types = {BaseEntity.class})
public interface BasicInformationProjection {

  /**
   * Gets id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  String getUpdatedAt();

  /**
   * Gets created at.
   *
   * @return the created at
   */
  String getCreatedAt();

  /**
   * Gets version.
   *
   * @return the version
   */
  Integer getVersion();
}

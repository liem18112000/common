package com.application.common.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * The interface Base projection.
 *
 * @param <ID> the type parameter
 */
public interface BaseProjection<ID extends Serializable> {

  /**
   * Gets id.
   *
   * @return the id
   */
  ID getId();

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
   * Access at string.
   *
   * @return the string
   */
  default String accessAt() {
    return Instant.now().toString();
  }

}

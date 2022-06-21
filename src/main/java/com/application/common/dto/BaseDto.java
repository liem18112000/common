package com.application.common.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The type Base dto.
 *
 * @param <ID> the type parameter
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class BaseDto<ID extends Serializable> implements Serializable, BaseProjection<ID> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1782592208752953678L;
  /**
   * The Access at.
   */
  final protected String accessAt = Instant.now().toString();
  /**
   * The Id.
   */
  protected ID id;
  /**
   * The Name.
   */
  protected String name;
  /**
   * The Description.
   */
  protected String description;
  /**
   * The Updated at.
   */
  protected String updatedAt;
  /**
   * The Created at.
   */
  protected String createdAt;
  /**
   * Instantiates a new Base dto.
   *
   * @param id          the id
   * @param name        the name
   * @param description the description
   * @param updatedAt   the updated at
   * @param createdAt   the created at
   */
  @Builder
  public BaseDto(ID id, String name, String description, String updatedAt, String createdAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.updatedAt = updatedAt;
    this.createdAt = createdAt;
  }
}

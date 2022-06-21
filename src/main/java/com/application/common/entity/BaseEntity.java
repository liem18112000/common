/*
 * Copyright (c) 2022. Liem Doan
 */

package com.application.common.entity;

import com.application.common.dto.BaseDto;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Base entity.
 *
 * @param <ID> the type parameter
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaseEntity
    <ID extends Serializable>
    implements Serializable, Cloneable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1580985846605044985L;
  /**
   * The Name.
   */
  @Column(name = "name")
  protected String name;
  /**
   * The Description.
   */
  @Column(name = "description", columnDefinition = "TEXT")
  protected String description;
  /**
   * The Updated at.
   */
  @Column(name = "updated_at")
  protected String updatedAt = Instant.now().toString();
  /**
   * The Created at.
   */
  @Column(name = "created_at")
  protected String createdAt = Instant.now().toString();
  /**
   * The Version.
   */
  @Column(name = "version")
  protected int version = 1;
  /**
   * The Is active.
   */
  @Column(name = "is_active")
  protected boolean isActive = true;
  /**
   * The Id.
   */
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ID id;

  /**
   * Update basic information t.
   *
   * @param <T>   the type parameter
   * @param <DTO> the type parameter
   * @param dto   the dto
   * @return the t
   */
  @SuppressWarnings("unchecked")
  public <T extends BaseEntity<ID>, DTO extends BaseDto<ID>> T updateBasicInformation(
      final DTO dto) {
    setName(dto.getName());
    setDescription(dto.getDescription());
    setUpdatedAt(LocalDateTime.now().toString());
    setVersion(this.version + 1);
    return (T) this;
  }

  /**
   * Deactivate t.
   *
   * @param <T> the type parameter
   * @return the t
   */
  @SuppressWarnings("unchecked")
  public <T extends BaseEntity<ID>> T deactivate() {
    if (this.isActive) {
      setActive(false);
    }
    return (T) this;
  }

  /**
   * Activate t.
   *
   * @param <T> the type parameter
   * @return the t
   */
  @SuppressWarnings("unchecked")
  public <T extends BaseEntity<ID>> T activate() {
    if (!this.isActive) {
      setActive(true);
    }
    return (T) this;
  }

  /**
   * {@inheritDoc}
   *
   * @return the object
   * @throws CloneNotSupportedException the clone not supported exception
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    var entity = new BaseEntity<ID>();
    entity.setId(this.getId());
    entity.setName(this.getName());
    entity.setDescription(this.getDescription());
    entity.setVersion(this.getVersion());
    entity.setUpdatedAt(this.getUpdatedAt());
    entity.setCreatedAt(this.getCreatedAt());
    entity.setActive(this.isActive());
    return super.clone();
  }
}

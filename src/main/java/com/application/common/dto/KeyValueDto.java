package com.application.common.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The type Key value dto.
 */
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KeyValueDto implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 124247620388979911L;

  /**
   * The Key.
   */
  private Serializable key;

  /**
   * The Value.
   */
  private Object value;

  /**
   * The Options.
   */
  private List<KeyValueDto> options = null;

  /**
   * Instantiates a new Key value dto.
   *
   * @param key   the key
   * @param value the value
   */
  public KeyValueDto(Serializable key, Object value) {
    this.key = key;
    this.value = value;
  }
}

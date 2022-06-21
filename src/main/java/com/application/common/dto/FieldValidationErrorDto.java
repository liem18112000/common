/*
 * Copyright (c) 2022. Liem Doan
 */

package com.application.common.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a generic ErrorDTO, it contains information about it like the error
 * message. The error flag is meant to provide easy identification of error when handled as JSON
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldValidationErrorDto implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4084802401555668790L;

  /**
   * The Error message.
   */
  private String field;

  /**
   * The Code.
   */
  private String code;

  /**
   * The Value.
   */
  private Object value;

  /**
   * The Error code.
   */
  private String message;
}

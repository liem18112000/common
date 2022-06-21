/*
 * Copyright (c) 2022. Liem Doan
 */

package com.application.common.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Validation error dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorDto implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 6539722605994534635L;

  /**
   * The Errors.
   */
  private List<FieldValidationErrorDto> errors;

  /**
   * The Entity.
   */
  private String entity;
}

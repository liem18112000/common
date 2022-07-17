package com.application.common.auth.token;

import java.io.Serializable;

/**
 * The interface Token validator.
 *
 * @param <T> the type parameter
 */
public interface TokenValidator<T extends Serializable> {


  /**
   * Validate boolean.
   *
   * @param parsedToken the parsed token
   * @return the boolean
   */
  boolean validate(T parsedToken);
}

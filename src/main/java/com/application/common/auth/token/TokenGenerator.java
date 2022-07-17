package com.application.common.auth.token;

import java.io.Serializable;

/**
 * The interface Token generator.
 *
 * @param <SUBJECT> the type parameter
 * @param <TOKEN>   the type parameter
 */
public interface TokenGenerator
    <SUBJECT extends Serializable, TOKEN extends Serializable> {

  /**
   * Generate token token.
   *
   * @param subject the subject
   * @return the token
   */
  TOKEN generateToken(SUBJECT subject);
}

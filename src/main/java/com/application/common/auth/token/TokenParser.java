package com.application.common.auth.token;

import java.io.Serializable;

/**
 * The interface Token parser.
 *
 * @param <TOKEN> the type parameter
 * @param <R>     the type parameter
 */
public interface TokenParser<
    TOKEN extends Serializable, R extends Serializable> {

  /**
   * Parse r.
   *
   * @param token the token
   * @return the r
   */
  R parse(TOKEN token);
}

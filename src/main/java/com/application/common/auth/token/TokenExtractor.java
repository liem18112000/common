package com.application.common.auth.token;

import java.io.Serializable;

/**
 * The interface Token extractor.
 *
 * @param <T> the type parameter
 */
public interface TokenExtractor<T extends Serializable> {

  /**
   * Gets user from token.
   *
   * @param parsedToken the parsed token
   * @return the user from token
   */
  T getUserFromToken(T parsedToken);
}

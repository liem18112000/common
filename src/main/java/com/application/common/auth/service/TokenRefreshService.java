package com.application.common.auth.service;

import java.io.Serializable;

/**
 * The interface Token refresh service.
 *
 * @param <CREDENTIAL> the type parameter
 * @param <TOKEN>      the type parameter
 */
public interface TokenRefreshService<
    CREDENTIAL extends Serializable,
    TOKEN extends Serializable> {

  /**
   * Refresh token.
   *
   * @param credential the refresh token
   * @return the token
   */
  TOKEN refreshToken(CREDENTIAL credential);

}

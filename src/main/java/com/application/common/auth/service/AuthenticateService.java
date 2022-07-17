package com.application.common.auth.service;

import java.io.Serializable;


/**
 * The interface Authenticate service.
 *
 * @param <CREDENTIAL> the type parameter
 * @param <TOKEN>      the type parameter
 */
public interface AuthenticateService<
    CREDENTIAL extends Serializable,
    TOKEN extends Serializable> {

  /**
   * Authenticate token.
   *
   * @param credential the credential
   * @return the token
   */
  TOKEN authenticate(CREDENTIAL credential);

}

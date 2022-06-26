package com.application.common.message.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Message origin.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOrigin implements Serializable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 3408252639126713335L;

  /**
   * The constant UNKNOWN_SERVICE.
   */
  public static final String UNKNOWN_SERVICE = "unknown service";

  /**
   * The constant UNKNOWN_ENDPOINT.
   */
  public static final String UNKNOWN_ENDPOINT = "unknown endpoint";

  /**
   * The constant UNKNOWN_SERVER.
   */
  public static final String UNKNOWN_SERVER = "unknown server";

  /**
   * The Origin service.
   */
  protected String originService = UNKNOWN_SERVICE;

  /**
   * The Origin endpoint.
   */
  protected String originEndpoint = UNKNOWN_ENDPOINT;

  /**
   * The Origin server.
   */
  protected String originServer = UNKNOWN_SERVER;
}

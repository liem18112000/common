package com.application.common.message;

import com.application.common.message.common.RestMessageOrigin;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * The type Rest message.
 *
 * @param <BODY> the body type parameter
 */
@NoArgsConstructor
@Data
public class RestMessage<BODY> implements Serializable, Cloneable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -1998267748464865180L;

  /**
   * The Rest message type.
   */
  protected RestMessageType restMessageType = RestMessageType.SEARCH;

  /**
   * The Headers.
   */
  protected Map<String, String> headers = new HashMap<>();

  /**
   * The Origin.
   */
  protected RestMessageOrigin origin = new RestMessageOrigin();

  /**
   * The Http status.
   */
  protected HttpStatus httpStatus;

  /**
   * The Body.
   */
  protected BODY body;

  /**
   * Clone rest message.
   *
   * @return the rest message
   */
  @Override
  public RestMessage<BODY> clone() {
    var message = new RestMessage<BODY>();
    message.setHttpStatus(this.getHttpStatus());
    message.setOrigin(this.getOrigin());
    message.setRestMessageType(this.getRestMessageType());
    message.setBody(this.getBody());
    message.setHeaders(this.getHeaders());
    return message;
  }
}

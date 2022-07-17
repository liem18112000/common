package com.application.common.message;

import com.application.common.message.common.MessageOrigin;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * The type Base message.
 *
 * @param <BODY> the type parameter
 */
@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class BaseMessage<BODY> implements Serializable, Cloneable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -1998267748464865180L;

  /**
   * The Message type.
   */
  protected String messageType;

  /**
   * The Headers.
   */
  protected Map<String, String> headers = new HashMap<>();

  /**
   * The Origin.
   */
  protected MessageOrigin origin = new MessageOrigin();

  /**
   * The Http status.
   */
  protected HttpStatus httpStatus;

  /**
   * The Body.
   */
  protected BODY body;

  /**
   * The Error message.
   */
  protected String errorMessage = null;

  /**
   * Clone base message.
   *
   * @return the base message
   */
  @Override
  public BaseMessage<BODY> clone() {
    var message = new BaseMessage<BODY>();
    message.setHttpStatus(this.getHttpStatus());
    message.setOrigin(this.getOrigin());
    message.setMessageType(this.getMessageType());
    message.setBody(this.getBody());
    message.setHeaders(this.getHeaders());
    return message;
  }
}

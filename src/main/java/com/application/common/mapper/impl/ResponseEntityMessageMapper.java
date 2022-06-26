package com.application.common.mapper.impl;

import com.application.common.mapper.MessageMapper;
import com.application.common.message.BaseMessage;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * The type Response entity message mapper.
 *
 * @param <BODY> the type parameter
 */
@Component
@Slf4j
public class ResponseEntityMessageMapper<BODY>
    implements MessageMapper<BODY, ResponseEntity<BODY>> {


  /**
   * The constant MESSAGE_IS_NULL.
   */
  public static final String MESSAGE_IS_NULL = "Message is null";

  /**
   * Map message to object t.
   *
   * @param message the message
   * @return the t
   */
  @Override
  public ResponseEntity<BODY> mapToObject(
      final BaseMessage<BODY> message) {
    if (message == null) {
      log.error(MESSAGE_IS_NULL);
      throw new IllegalArgumentException(MESSAGE_IS_NULL);
    }
    return ResponseEntity.status(message.getHttpStatus())
        .headers(this.getHttpHeaders(message.getHeaders()))
        .body(message.getBody());
  }

  /**
   * Gets http headers.
   *
   * @param headers the headers
   * @return the http headers
   */
  private HttpHeaders getHttpHeaders(
      final Map<String, String> headers) {
    HttpHeaders httpHeaders = new HttpHeaders();
    if (headers != null) {
      headers.forEach(httpHeaders::set);
    }
    return httpHeaders;
  }

  /**
   * Map responseEntity to message rest message.
   *
   * @param responseEntity the responseEntity
   * @return the rest message
   */
  @Override
  public BaseMessage<BODY> mapToMessage(final ResponseEntity<BODY> responseEntity) {
    var message = new BaseMessage<BODY>();
    message.setHttpStatus(responseEntity.getStatusCode());
    message.setBody(responseEntity.getBody());
    message.setHeaders(responseEntity.getHeaders().toSingleValueMap());
    return null;
  }
}

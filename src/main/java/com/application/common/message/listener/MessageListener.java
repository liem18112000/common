package com.application.common.message.listener;

import com.application.common.dto.BaseDto;
import com.application.common.message.BaseMessage;
import java.io.Serializable;

/**
 * The interface Message listener.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
public interface MessageListener
    <ID extends Serializable, DTO extends BaseDto<ID>> {

  /**
   * Handle request rest message.
   *
   * @param restMessage the rest message
   * @return the rest message
   */
  BaseMessage<DTO> handleRequest(final BaseMessage<DTO> restMessage);
}

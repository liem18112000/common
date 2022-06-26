package com.application.common.message.client;

import com.application.common.dto.BaseDto;
import com.application.common.message.BaseMessage;
import java.io.Serializable;

/**
 * The interface Message query client.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
public interface MessageQueryClient
    <ID extends Serializable, DTO extends BaseDto<ID>> {


  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  BaseMessage<DTO> getById(ID id);

  /**
   * Search rest message.
   *
   * @param dto the dto
   * @return the rest message
   */
  BaseMessage<DTO> search(DTO dto);

}

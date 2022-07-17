package com.application.common.message.client.impl;

import com.application.common.dto.BaseDto;
import com.application.common.message.BaseMessage;
import com.application.common.message.RestMessageType;
import com.application.common.message.client.MessageCommandClient;
import com.application.common.message.client.MessageQueryClient;
import com.application.common.message.common.MessageOrigin;
import java.io.Serializable;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * The type Message client.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
@Service
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Slf4j
public abstract class AbstractMessageClient
    <ID extends Serializable, DTO extends BaseDto<ID>>
    implements MessageQueryClient<ID, DTO>, MessageCommandClient<ID, DTO> {

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @SuppressWarnings("unchecked")
  @Override
  public BaseMessage<DTO> getById(final ID id) {
    return getMessageClientTemplate(() -> {
      DTO dto = (DTO) new BaseDto<ID>();
      dto.setId(id);
      var request = prepareRequest(dto,
          RestMessageType.GET_BY_ID.name());
      log.info("Get by id: {}", request);
      return request;
    }, this::handleFailedRequest);
  }

  /**
   * Search base message.
   *
   * @param dto the dto
   * @return the base message
   */
  @Override
  public BaseMessage<DTO> search(final DTO dto) {
    return getMessageClientTemplate(() -> {
      var request = prepareRequest(dto,
          RestMessageType.SEARCH.name());
      log.info("Search: {}", request);
      return request;
    }, this::handleFailedSearch);
  }

  /**
   * Create base message.
   *
   * @param dto the dto
   * @return the base message
   */
  @Override
  public BaseMessage<DTO> create(DTO dto) {
    return getMessageClientTemplate(() -> {
      var request = prepareRequest(dto,
          RestMessageType.SAVE.name());
      log.info("Create: {}", request);
      return request;
    }, this::handleFailedRequest);
  }

  /**
   * Update base message.
   *
   * @param id  the id
   * @param dto the dto
   * @return the base message
   */
  @Override
  public BaseMessage<DTO> update(ID id, DTO dto) {
    return getMessageClientTemplate(() -> {
      dto.setId(id);
      var request = prepareRequest(dto,
          RestMessageType.UPDATE_BY_ID.name());
      log.info("Update by id: {}", request);
      return request;
    }, this::handleFailedRequest);
  }

  /**
   * Handle failed request base message.
   *
   * @param request the request
   * @return the base message
   */
  protected BaseMessage<DTO> handleFailedRequest(BaseMessage<DTO> request) {
    request.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return request;
  }

  /**
   * Delete base message.
   *
   * @param id the id
   * @return the base message
   */
  @Override
  @SuppressWarnings("unchecked")
  public BaseMessage<DTO> delete(ID id) {
    return getMessageClientTemplate(() -> {
      DTO dto = (DTO) new BaseDto<ID>();
      dto.setId(id);
      var request = prepareRequest(dto,
          RestMessageType.DELETE_BY_ID.name());
      log.info("Delete by id: {}", request);
      return request;
    }, this::handleFailedRequest);
  }


  /**
   * Gets message client template.
   *
   * @param prepareRequest the prepare request
   * @param handleFailed   the handle failed
   * @return the message client template
   */
  @SuppressWarnings("unchecked")
  protected BaseMessage<DTO> getMessageClientTemplate(
      Supplier<BaseMessage<DTO>> prepareRequest,
      Function<BaseMessage<DTO>, BaseMessage<DTO>> handleFailed) {
    final var request = prepareRequest.get();
    try {
      return (BaseMessage<DTO>) sendMessage(request);
    } catch (Exception exception) {
      exception.printStackTrace();
      return handleFailed.apply(request);
    }
  }

  /**
   * Send message object.
   *
   * @param request the request
   * @return the object
   */
  protected abstract Object sendMessage(BaseMessage<DTO> request);

  /**
   * Prepare request base message.
   *
   * @param dto         the dto
   * @param messageType the message type
   * @return the base message
   */
  protected BaseMessage<DTO> prepareRequest(
      final DTO dto, final String messageType) {
    var request = new BaseMessage<DTO>();
    request.setBody(dto);
    request.setOrigin(this.createRequestOrigin());
    request.setMessageType(messageType);
    return request;
  }

  /**
   * Handle failed search base message.
   *
   * @param request the request
   * @return the base message
   */
  protected BaseMessage<DTO> handleFailedSearch(final BaseMessage<DTO> request) {
    var failedResponse = new BaseMessage<DTO>();
    failedResponse.setBody(request.getBody());
    failedResponse.setOrigin(this.createRequestOrigin());
    request.setMessageType(RestMessageType.SEARCH.name());
    failedResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return failedResponse;
  }

  /**
   * Create request origin message origin.
   *
   * @return the message origin
   */
  protected abstract MessageOrigin createRequestOrigin();
}

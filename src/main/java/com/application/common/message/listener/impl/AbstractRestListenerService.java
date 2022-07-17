package com.application.common.message.listener.impl;

import static com.application.common.specifications.BaseSpecification.Spec;
import static com.application.common.specifications.SearchCriteria.criteriaEqual;
import static com.application.common.specifications.SearchCriteria.criteriaTimestampGreaterThanOrEqual;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import com.application.common.message.BaseMessage;
import com.application.common.message.RestMessageType;
import com.application.common.message.common.MessageOrigin;
import com.application.common.message.listener.MessageListener;
import com.application.common.service.CommandService;
import com.application.common.service.QueryService;
import com.application.common.specifications.BaseSpecification;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * The type Abstract rest listener service.
 *
 * @param <ID>              the type parameter
 * @param <DTO>             the type parameter
 * @param <ENTITY>          the type parameter
 * @param <QUERY_SERVICE>   the type parameter
 * @param <COMMAND_SERVICE> the type parameter
 */
@Slf4j
abstract public class AbstractRestListenerService<
    ID extends Serializable,
    DTO extends BaseDto<ID>,
    ENTITY extends BaseEntity<ID>,
    QUERY_SERVICE extends QueryService<ID, ENTITY, DTO>,
    COMMAND_SERVICE extends CommandService<ID, DTO>
    > implements MessageListener<ID, DTO> {

  /**
   * The Query service.
   */
  protected final QUERY_SERVICE queryService;

  /**
   * The Command service.
   */
  protected final COMMAND_SERVICE commandService;

  /**
   * The Request handle map.
   */
  protected Map<String, Function<BaseMessage<DTO>, BaseMessage<DTO>>> requestHandleMap;

  /**
   * Instantiates a new Abstract rest listener service.
   *
   * @param queryService   the query service
   * @param commandService the command service
   */
  public AbstractRestListenerService(
      QUERY_SERVICE queryService, COMMAND_SERVICE commandService) {
    this.queryService = queryService;
    this.commandService = commandService;
    requestHandleMap = initRequestHandleMap();
  }

  /**
   * Init request handle map map.
   *
   * @return the map
   */
  protected Map<String, Function<BaseMessage<DTO>, BaseMessage<DTO>>> initRequestHandleMap() {
    this.requestHandleMap = new HashMap<>();
    requestHandleMap.put(RestMessageType.GET_BY_ID.name(), this::handleGetById);
    requestHandleMap.put(RestMessageType.SEARCH.name(), this::handleSearch);
    requestHandleMap.put(RestMessageType.SAVE.name(), this::handleSave);
    requestHandleMap.put(RestMessageType.UPDATE_BY_ID.name(), this::handleUpdateById);
    requestHandleMap.put(RestMessageType.DELETE_BY_ID.name(), this::handleDeleteById);
    return requestHandleMap;
  }

  /**
   * Handle request base message.
   *
   * @param message the message
   * @return the base message
   */
  public BaseMessage<DTO> handleRequest(
      final BaseMessage<DTO> message) {
    final var messageType = message.getMessageType();
    final var handler=
        this.requestHandleMap.get(messageType);
    if (handler == null) {
      return this.handleDefault(message);
    }
    return handler.apply(message);
  }

  /**
   * Handle search base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleSearch(
      final BaseMessage<DTO> message) {
    final var body = message.getBody();
    var response = message.clone();
    if (body == null) {
      return resolveBadRequest(response, "Not found by DTO");
    }
    try {
      final var dto = this.queryService.search(this.createSpecification(body));
      response.setOrigin(createResponseOrigin());
      if (dto.isEmpty()) {
        resolveNotFound(response,
            "Message body is null => return 404 response");
      } else {
        response.setBody(dto.get(0));
        response.setHttpStatus(HttpStatus.OK);
      }
      return response;
    } catch (Exception exception) {
      exception.printStackTrace();
      return resolveBadRequest(response,
          "Search failed => return 400 response");
    }
  }

  /**
   * Create specification specification.
   *
   * @param dto the dto
   * @return the specification
   */
  protected Specification<ENTITY> createSpecification(final DTO dto) {
    return BaseSpecification
        .<ENTITY>Spec(criteriaEqual("name",
            Optional.ofNullable(dto.getName()).orElse("")))
        .and(Spec(criteriaEqual("description",
            Optional.ofNullable(dto.getDescription()).orElse(""))))
        .and(Spec(StringUtils.hasText(dto.getCreatedAt()) ?
            criteriaTimestampGreaterThanOrEqual("createdAt", dto.getCreatedAt()) :
            criteriaEqual("createdAt", dto.getCreatedAt())))
        .and(Spec(StringUtils.hasText(dto.getCreatedAt()) ?
            criteriaTimestampGreaterThanOrEqual("createdAt", dto.getCreatedAt()) :
            criteriaEqual("createdAt", dto.getCreatedAt())));
  }

  /**
   * Handle default base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleDefault(
      final BaseMessage<DTO> message) {
    log.error("Rest message type unknown: {}", message.getMessageType());
    var response = message.clone();
    response.setOrigin(createResponseOrigin());
    response.setHttpStatus(HttpStatus.BAD_REQUEST);
    return response;
  }

  /**
   * Handle update by id base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleUpdateById(
      final BaseMessage<DTO> message) {
    final var body = message.getBody();
    var response = message.clone();
    if (body == null) {
      return resolveBadRequest(response,
          "Message body is null => return 400 response");
    }
    final var id = body.getId();
    if (id == null) {
      return resolveBadRequest(response,
          "Message body id is null => return 400 response");
    }
    try {
      final var dto = this.commandService.update(id, body);
      response.setOrigin(createResponseOrigin());
      response.setBody(dto);
      response.setHttpStatus(HttpStatus.OK);
      return response;
    } catch (Exception exception) {
      exception.printStackTrace();
      return resolveBadRequest(response,
          "Update failed => return 400 response");
    }
  }

  /**
   * Handle delete by id base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleDeleteById(
      final BaseMessage<DTO> message) {
    final var body = message.getBody();
    var response = message.clone();
    if (body == null) {
      return resolveBadRequest(response,
          "Message body is null => return 400 response");
    }
    final var id = body.getId();
    if (id == null) {
      return resolveNotFound(response,
          "Message body id is null => return 404 response");
    }
    try {
      final var dto = this.queryService.getById(id);
      response.setBody(dto);
      response.setHttpStatus(HttpStatus.NO_CONTENT);
      response.setOrigin(createResponseOrigin());
      return response;
    } catch (Exception exception) {
      exception.printStackTrace();
      return resolveNotFound(response,
          "Asset is not found => return 404 response");
    }
  }

  /**
   * Handle save base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleSave(
      final BaseMessage<DTO> message) {
    final var body = message.getBody();
    var response = message.clone();
    if (body == null) {
      return resolveBadRequest(response,
          "Message body is null => return 400 response");
    }
    try {
      final var dto = this.commandService.create(body);
      response.setOrigin(createResponseOrigin());
      response.setBody(dto);
      response.setHttpStatus(HttpStatus.CREATED);
      return response;
    } catch (Exception exception) {
      exception.printStackTrace();
      return resolveBadRequest(response,
          "Save failed => return 400 response");
    }
  }

  /**
   * Handle get by id base message.
   *
   * @param message the message
   * @return the base message
   */
  protected BaseMessage<DTO> handleGetById(
      final BaseMessage<DTO> message) {
    final var body = message.getBody();
    var response = message.clone();
    if (body == null) {
      return resolveNotFound(response,
          "Message body is null => return 404 response");
    }

    final var id = body.getId();
    if (id == null) {
      return resolveNotFound(response,
          "Message body id is null => return 404 response");
    }
    try {
      final var dto = this.queryService.getById(id);
      response.setBody(dto);
      response.setHttpStatus(HttpStatus.OK);
      response.setOrigin(createResponseOrigin());
      return response;
    } catch (Exception exception) {
      exception.printStackTrace();
      return resolveNotFound(response,
          "Asset is not found => return 404 response");
    }
  }

  /**
   * Resolve bad request base message.
   *
   * @param response the response
   * @param message  the message
   * @return the base message
   */
  protected BaseMessage<DTO> resolveBadRequest(
      BaseMessage<DTO> response, String message) {
    log.error(message);
    response.setErrorMessage(message);
    response.setHttpStatus(HttpStatus.BAD_REQUEST);
    response.setOrigin(createResponseOrigin());
    return response;
  }

  /**
   * Resolve not found base message.
   *
   * @param response the response
   * @param message  the message
   * @return the base message
   */
  protected BaseMessage<DTO> resolveNotFound(
      BaseMessage<DTO> response, String message) {
    log.error(message);
    response.setErrorMessage(message);
    response.setHttpStatus(HttpStatus.NOT_FOUND);
    response.setOrigin(createResponseOrigin());
    return response;
  }

  /**
   * Create response origin message origin.
   *
   * @return the message origin
   */
  protected abstract MessageOrigin createResponseOrigin();

}

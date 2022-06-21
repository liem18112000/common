package com.application.common.service.util;

import java.util.Objects;
import java.util.function.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Service util.
 */
public class ServiceUtil {

  /**
   * Throw exception for not found entity by id supplier.
   *
   * @param id the id
   * @return the supplier
   */
  public static Supplier<ResponseStatusException> throwExceptionForNotFoundEntityById(Object id) {
    return () -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("Entity is not found by id: %s", id));
  }

  /**
   * Throw exception for not found entity by name supplier.
   *
   * @param name the name
   * @return the supplier
   */
  public static Supplier<ResponseStatusException> throwExceptionForNotFoundEntityByName(
      String name) {
    return () -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("Entity is not found by name: %s", name));
  }

  /**
   * Validate not null.
   *
   * @param object   the object
   * @param property the property
   */
  public static void validateNotNull(Object object, String property) {
    if (Objects.isNull(object)) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, String.format("%s is null", property));
    }
  }

}

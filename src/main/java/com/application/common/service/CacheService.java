package com.application.common.service;

import java.io.Serializable;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * The interface Cache service.
 *
 * @param <KEY>   the type parameter
 * @param <VALUE> the type parameter
 */
public interface CacheService
    <KEY extends Serializable, VALUE> {

  /**
   * Get value.
   *
   * @param key the key
   * @return the value
   */
  VALUE get(KEY key);

  /**
   * Get value.
   *
   * @param key     the key
   * @param getReal the get real
   * @return the value
   */
  VALUE get(KEY key, Supplier<VALUE> getReal);

  /**
   * Get value.
   *
   * @param key      the key
   * @param getReal  the get real
   * @param duration the duration
   * @return the value
   */
  VALUE get(KEY key, Supplier<VALUE> getReal, Duration duration);

  /**
   * Cache value.
   *
   * @param key   the key
   * @param value the value
   * @return the value
   */
  VALUE cache(KEY key, VALUE value);

  /**
   * Cache value.
   *
   * @param key      the key
   * @param value    the value
   * @param duration the duration
   * @return the value
   */
  VALUE cache(KEY key, VALUE value, Duration duration);

}

package com.application.common.utils;

import org.springframework.data.domain.Pageable;

/**
 * The interface Customize page request.
 */
public interface CustomizePageRequest {

  /**
   * The constant MIN_PAGE_VALUE.
   */
  int MIN_PAGE_VALUE = 0;
  /**
   * The constant MAX_SIZE_VALUE.
   */
  int MAX_SIZE_VALUE = Integer.MAX_VALUE;

  /**
   * Gets pageable.
   *
   * @return the pageable
   */
  Pageable getPageable();

  /**
   * Filter size int.
   *
   * @param size the size
   * @return the int
   */
  default int filterSize(int size) {
    return size <= 0 ? MAX_SIZE_VALUE : size;
  }

  /**
   * Filter page int.
   *
   * @param page the page
   * @return the int
   */
  default int filterPage(int page) {
    return Math.max(page, MIN_PAGE_VALUE);
  }
}

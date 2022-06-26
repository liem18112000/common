package com.application.common.utils.page;

import java.util.Arrays;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * The type Base customize page request.
 */
@Data
public class BaseCustomizePageRequest implements CustomizePageRequest {

  /**
   * The constant ASC.
   */
// Sort constant
  static public final String ASC = "asc";
  /**
   * The Desc.
   */
  static public final String DESC = "desc";

  /**
   * The Page.
   */
// Pageable attributes
  protected int page = 0;
  /**
   * The Size.
   */
  protected int size = Integer.MAX_VALUE;
  /**
   * The Sort.
   */
  protected String sort;

  /**
   * Gets pageable.
   *
   * @return the pageable
   */
  @SneakyThrows
  public Pageable getPageable() {
    try {
      if (StringUtils.hasText(this.sort)) {
        return PageRequest.of(
            filterPage(page),
            filterSize(size),
            this.getOrders());
      }
      return PageRequest.of(
          filterPage(page),
          filterSize(size));
    } catch (Exception exception) {
      throw new Exception(exception.getMessage());
    }
  }

  /**
   * Gets orders.
   *
   * @return the orders
   */
  protected Sort getOrders() {
    this.sort = this.sort.replaceAll("\\s+", "");
    final var arr = Arrays.asList(this.sort.split(","));
    final var sortOrder = arr.get(arr.size() - 1);
    final var sortField = arr.get(0);
    Sort sorts = Sort.by(sortField);
    if (sortOrder.equals(ASC)) {
      sorts = sorts.ascending();
    } else if (sortOrder.equals(DESC)) {
      sorts = sorts.descending();
    }
    return sorts;
  }
}

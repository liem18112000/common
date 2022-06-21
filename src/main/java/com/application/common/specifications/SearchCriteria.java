package com.application.common.specifications;

import lombok.Builder;
import lombok.Data;

/**
 * The type Search criteria.
 */
@Data
@Builder(toBuilder = true)
public class SearchCriteria {

  /**
   * The Key.
   */
  private String key;
  /**
   * The Operation.
   */
  private SearchOperation operation;
  /**
   * The Value.
   */
  private Object value;

  /**
   * The Strict.
   */
  @Builder.Default
  private boolean strict = false;

  /**
   * The Ignore empty.
   */
  @Builder.Default
  private boolean ignoreEmpty = true;

  /**
   * Criteria less than search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaLessThan(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.LESS_THAN).value(value)
        .build();
  }

  /**
   * Criteria less than or equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaLessThanOrEqual(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.LESS_THAN_OR_EQUAL)
        .value(value).build();
  }

  /**
   * Criteria greater than search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaGreaterThan(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.GREATER_THAN).value(value)
        .build();
  }

  /**
   * Criteria timestamp greater than search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaTimestampGreaterThan(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.TIMESTAMP_GREATER_THAN)
        .value(value).build();
  }

  /**
   * Criteria timestamp less than search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaTimestampLessThan(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.TIMESTAMP_LESS_THAN)
        .value(value).build();
  }

  /**
   * Criteria timestamp greater than or equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaTimestampGreaterThanOrEqual(String key, Object value) {
    return SearchCriteria.builder().key(key)
        .operation(SearchOperation.TIMESTAMP_GREATER_THAN_OR_EQUAL).value(value).build();
  }

  /**
   * Criteria timestamp less than or equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaTimestampLessThanOrEqual(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.TIMESTAMP_LESS_THAN_OR_EQUAL)
        .value(value).build();
  }

  /**
   * Criteria greater than or equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaGreaterThanOrEqual(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.GREATER_THAN_OR_EQUAL)
        .value(value).build();
  }

  /**
   * Criteria equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaEqual(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.EQUAL).value(value).build();
  }

  /**
   * Criteria strictly equal search criteria.
   *
   * @param key         the key
   * @param value       the value
   * @param ignoreEmpty the ignore empty
   * @return the search criteria
   */
  static public SearchCriteria criteriaStrictlyEqual(String key, Object value,
      boolean ignoreEmpty) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.EQUAL)
        .value(value).strict(true).ignoreEmpty(ignoreEmpty).build();
  }

  /**
   * Criteria not equal search criteria.
   *
   * @param key   the key
   * @param value the value
   * @return the search criteria
   */
  static public SearchCriteria criteriaNotEqual(String key, Object value) {
    return SearchCriteria.builder().key(key).operation(SearchOperation.NOT_EQUAL).value(value)
        .build();
  }
}

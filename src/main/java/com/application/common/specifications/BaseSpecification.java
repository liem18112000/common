package com.application.common.specifications;

import java.sql.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * The type Base specification.
 *
 * @param <T> the type parameter
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class BaseSpecification<T> implements Specification<T> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -3984908227940188335L;

  /**
   * The Criteria.
   */
  private SearchCriteria criteria;

  /**
   * Spec base specification.
   *
   * @param <T>            the type parameter
   * @param searchCriteria the search criteria
   * @return the base specification
   */
  static public <T> BaseSpecification<T> Spec(SearchCriteria searchCriteria) {
    var spec = new BaseSpecification<T>();
    spec.setCriteria(searchCriteria);
    return spec;
  }

  /**
   * To predicate predicate.
   *
   * @param root    the root
   * @param query   the query
   * @param builder the builder
   * @return the predicate
   */
  @Override
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    final SearchOperation operation = criteria.getOperation();
    if (operation.equals(SearchOperation.GREATER_THAN_OR_EQUAL)) {
      return builder.greaterThanOrEqualTo(
          root.get(criteria.getKey()), criteria.getValue().toString());
    } else if (operation.equals(SearchOperation.GREATER_THAN)) {
      return builder.greaterThan(
          root.get(criteria.getKey()), criteria.getValue().toString());
    } else if (operation.equals(SearchOperation.TIMESTAMP_GREATER_THAN)) {
      return builder.greaterThan(
          builder.function("DATE", Date.class, root.get(criteria.getKey())),
          builder.function("DATE", Date.class, builder.literal(criteria.getValue().toString()))
      );
    } else if (operation.equals(SearchOperation.TIMESTAMP_LESS_THAN)) {
      return builder.lessThan(
          builder.function("DATE", Date.class, root.get(criteria.getKey())),
          builder.function("DATE", Date.class, builder.literal(criteria.getValue().toString()))
      );
    } else if (operation.equals(SearchOperation.TIMESTAMP_GREATER_THAN_OR_EQUAL)) {
      return builder.greaterThanOrEqualTo(
          builder.function("DATE", Date.class, root.get(criteria.getKey())),
          builder.function("DATE", Date.class, builder.literal(criteria.getValue().toString()))
      );
    } else if (operation.equals(SearchOperation.TIMESTAMP_LESS_THAN_OR_EQUAL)) {
      return builder.lessThanOrEqualTo(
          builder.function("DATE", Date.class, root.get(criteria.getKey())),
          builder.function("DATE", Date.class, builder.literal(criteria.getValue().toString()))
      );
    } else if (operation.equals(SearchOperation.LESS_THAN_OR_EQUAL)) {
      return builder.lessThanOrEqualTo(
          root.get(criteria.getKey()), criteria.getValue().toString());
    } else if (operation.equals(SearchOperation.LESS_THAN)) {
      return builder.lessThan(
          root.get(criteria.getKey()), criteria.getValue().toString());
    } else if (operation.equals(SearchOperation.EQUAL)) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        if (criteria.isStrict()) {
          if (criteria.getValue().toString().isEmpty() && criteria.isIgnoreEmpty()) {
            return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
          }
          return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
    } else if (operation.equals(SearchOperation.NOT_EQUAL)) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.notLike(
            root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}

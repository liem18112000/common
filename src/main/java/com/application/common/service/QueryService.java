package com.application.common.service;


import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * The interface Query service.
 *
 * @param <ID>     the type parameter
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 */
public interface QueryService
    <ID extends Serializable,
        ENTITY extends BaseEntity<ID>,
        DTO extends BaseDto<ID>> {

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  DTO getById(ID id);

  /**
   * Search page.
   *
   * @param specification the specification
   * @param pageable      the pageable
   * @return the page
   */
  Page<DTO> search(Specification<ENTITY> specification, Pageable pageable);

  /**
   * Search list.
   *
   * @param specification the specification
   * @return the list
   */
  List<DTO> search(Specification<ENTITY> specification);

  /**
   * Gets all.
   *
   * @param pageable the pageable
   * @return the all
   */
  Page<DTO> getAll(Pageable pageable);

  /**
   * Gets all.
   *
   * @return the all
   */
  List<DTO> getAll();
}

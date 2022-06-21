package com.application.common.service.impl;

import static com.application.common.service.util.ServiceUtil.throwExceptionForNotFoundEntityById;
import static com.application.common.service.util.ServiceUtil.validateNotNull;

import com.application.common.dto.BaseDto;
import com.application.common.entity.BaseEntity;
import com.application.common.mapper.Mapper;
import com.application.common.repository.BaseRepository;
import com.application.common.service.QueryService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Base query service.
 *
 * @param <ID>     the type parameter
 * @param <ENTITY> the type parameter
 * @param <DTO>    the type parameter
 * @param <MAPPER> the type parameter
 * @param <REPO>   the type parameter
 */
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BaseQueryService<
    ID extends Serializable,
    ENTITY extends BaseEntity<ID>,
    DTO extends BaseDto<ID>,
    MAPPER extends Mapper<ENTITY, DTO, ID>,
    REPO extends BaseRepository<ENTITY, ID>
    > implements QueryService<ID, ENTITY, DTO> {

  /**
   * The Repo.
   */
  protected final REPO repo;

  /**
   * The Mapper.
   */
  protected final MAPPER mapper;

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   */
  @Override
  @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
  public DTO getById(final ID id) {
    validateNotNull(id, "ID");
    final var entity = this.repo.findById(id)
        .orElseThrow(throwExceptionForNotFoundEntityById(id));
    return this.mapper.mapToDto(entity);
  }

  /**
   * Search page.
   *
   * @param specification the specification
   * @param pageable      the pageable
   * @return the page
   */
  @Override
  @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
  public Page<DTO> search(
      final Specification<ENTITY> specification, final Pageable pageable) {
    validateNotNull(specification, "specification");
    validateNotNull(pageable, "pageable");
    final var entities = this.repo.findAll(
        specification, pageable);
    return entities.map(this.mapper::mapToDto);
  }

  /**
   * Search list.
   *
   * @param specification the specification
   * @return the list
   */
  @Override
  @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
  public List<DTO> search(Specification<ENTITY> specification) {
    validateNotNull(specification, "specification");
    final var entities = this.repo.findAll(specification);
    return entities.stream().map(this.mapper::mapToDto)
        .collect(Collectors.toList());
  }

  /**
   * Gets all.
   *
   * @param pageable the pageable
   * @return the all
   */
  @Override
  @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
  public Page<DTO> getAll(final Pageable pageable) {
    validateNotNull(pageable, "pageable");
    final var entities = this.repo.findAll(pageable);
    return entities.map(this.mapper::mapToDto);
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @Override
  @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
  public List<DTO> getAll() {
    final var entities = this.repo.findAll();
    return entities.stream().map(this.mapper::mapToDto)
        .collect(Collectors.toList());
  }
}

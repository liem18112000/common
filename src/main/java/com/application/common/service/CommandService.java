package com.application.common.service;

import com.application.common.dto.BaseDto;
import java.io.Serializable;

/**
 * The interface Command service.
 *
 * @param <ID>  the type parameter
 * @param <DTO> the type parameter
 */
public interface CommandService
    <ID extends Serializable,
        DTO extends BaseDto<ID>> {

  /**
   * Create dto.
   *
   * @param dto the dto
   * @return the dto
   */
  DTO create(DTO dto);

  /**
   * Update dto.
   *
   * @param id  the id
   * @param dto the dto
   * @return the dto
   */
  DTO update(ID id, DTO dto);

  /**
   * Delete dto.
   *
   * @param id the id
   * @return the dto
   */
  DTO delete(ID id);
}

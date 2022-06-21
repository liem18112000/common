package com.application.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Entity action.
 */
@AllArgsConstructor
public enum EntityAction {
  /**
   * Failed entity action.
   */
  FAILED(-2L),
  /**
   * Create entity action.
   */
  CREATE(1L),
  /**
   * Update extra entity action.
   */
  UPDATE_EXTRA(3L),
  /**
   * Update basic entity action.
   */
  UPDATE_BASIC(2L),
  /**
   * Link parent entity action.
   */
  LINK_PARENT(4L),
  /**
   * Unlink parent entity action.
   */
  UNLINK_PARENT(5L),
  /**
   * Disable audit entity action.
   */
  DISABLE_AUDIT(11L),
  /**
   * Enable audit entity action.
   */
  ENABLE_AUDIT(10L),
  /**
   * Deactivate entity action.
   */
  DEACTIVATE(8L),
  /**
   * Reactivate entity action.
   */
  REACTIVATE(9L);

  /**
   * The Event type id.
   */
  @Getter
  private final Long eventTypeId;
}

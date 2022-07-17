package com.application.common.auth.token.impl;

import com.application.common.auth.token.TokenParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * The type Digest token parser.
 */
@Slf4j
@Service
public class DigestTokenParser implements TokenParser<String, String> {

  /**
   * The constant TOKEN_PREFIX.
   */
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * Parse string.
   *
   * @param token the token
   * @return the string
   */
  @Override
  public String parse(final String token) {
    if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
      return token.substring(TOKEN_PREFIX.length());
    }
    throw new IllegalArgumentException(
        String.format("Token is not valid: %s", token));
  }
}

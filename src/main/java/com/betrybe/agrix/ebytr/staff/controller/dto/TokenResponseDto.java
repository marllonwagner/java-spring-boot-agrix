package com.betrybe.agrix.ebytr.staff.controller.dto;

/**
 * Data Transfer Object (DTO) representing the response containing an authentication token.
 */
public class TokenResponseDto {
  private String token;

  /**
   * Constructs a TokenResponseDto with the provided token.
   *
   * @param token The authentication token.
   */
  public TokenResponseDto(String token) {
    this.token = token;
  }

  /**
   * Get the authentication token.
   *
   * @return The authentication token.
   */
  public String getToken() {
    return token;
  }

  /**
   * Set the authentication token.
   *
   * @param token The authentication token to set.
   */
  public void setToken(String token) {
    this.token = token;
  }
}

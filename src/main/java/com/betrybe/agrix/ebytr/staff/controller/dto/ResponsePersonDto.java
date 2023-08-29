package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Data Transfer Object (DTO) representing a response containing person data.
 */
public record ResponsePersonDto(Long id, String username, Role role) {

  // No additional methods or documentation needed for this simple DTO.
}

package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Data Transfer Object (DTO) representing a person.
 */
public record PersonDto(Long id, String username, String password, Role role) {

  /**
   * Converts the PersonDto object to a Person entity.
   *
   * @return A new Person entity created from the data in the DTO.
   */
  public Person toPerson() {
    return new Person(id, username, password, role);
  }
}

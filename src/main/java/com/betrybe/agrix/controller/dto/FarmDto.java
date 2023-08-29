package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record FarmDto(Long id, Double size, String name) {

  /**
   * Converte o DTO FarmDTO para uma entidade Farm.
   *
   * @return Uma instância da entidade Farm.
   */
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}

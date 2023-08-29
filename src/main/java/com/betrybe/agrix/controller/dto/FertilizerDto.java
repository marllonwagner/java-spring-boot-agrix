package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * DTO (Data Transfer Object) que representa uma fertilizer para fins de transferência de dados.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Converte o DTO FertilizerDTO para uma entidade Fertilizer.
   *
   * @return Uma instância da entidade Fertilizer.
   */
  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}

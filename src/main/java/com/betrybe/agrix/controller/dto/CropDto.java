package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record CropDto(Long id, String name, Double plantedArea,
                      Long farmId, LocalDate plantedDate, LocalDate harvestDate) {

  /**
   * Converte o DTO FarmDTO para uma entidade Farm.
   *
   * @return Uma instância da entidade Farm.
   */
  public Crop toCrop(Long farmId) {
    Crop crop = new Crop(id, name, plantedArea, plantedDate, harvestDate);
    crop.setFarm(new Farm(farmId, null, null));
    return crop;
  }
}

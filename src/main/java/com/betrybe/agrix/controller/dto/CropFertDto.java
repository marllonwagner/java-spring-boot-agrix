package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record CropFertDto(Crop crop, Fertilizer fertilizer) {

  /**
   * Converte o DTO FarmDTO para uma entidade Farm.
   *
   * @return Uma instância da entidade Farm.
   */
  public CropFertilizer toCropFert() {
    CropFertilizer cropFertilizer = new CropFertilizer();
    cropFertilizer.setCrop(crop);
    cropFertilizer.setFertilizer(fertilizer);
    return cropFertilizer;
  }
}

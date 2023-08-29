package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropFertRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para operações relacionadas à entidade Crop.
 */

@Service
public class CropFertService {

  private CropFertRepository cropFertRepository;

  @Autowired
  public CropFertService(CropFertRepository cropFertRepository) {
    this.cropFertRepository = cropFertRepository;
  }

  public CropFertilizer insertCropFert(CropFertilizer cropFertilizer) {
    return cropFertRepository.save(cropFertilizer);
  }

  public Optional<List<Fertilizer>> getFertilizersByCropId(Long cropId) {
    return Optional.ofNullable(cropFertRepository.findAllByCropId(cropId));
  }
}

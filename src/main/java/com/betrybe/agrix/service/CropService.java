package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Classe de serviço para operações relacionadas à entidade Crop.
 */

@Service
public class CropService {

  private CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public Optional<List<Crop>> getCropsByFarmId(Long farmId) {
    return Optional.ofNullable(cropRepository.findAllByFarmId(farmId));
  }

  public Optional<List<Crop>> getAllCrops() {
    return Optional.ofNullable(cropRepository.findAll());
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getCropsBetweenDates(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }
}

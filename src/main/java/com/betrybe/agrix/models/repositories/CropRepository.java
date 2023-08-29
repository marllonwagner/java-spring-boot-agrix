package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface que define um repositório para entidades do tipo Crop.
 * Esta interface estende JpaRepository, fornecendo operações CRUD (Create, Read, Update, Delete).
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findAllByFarmId(Long farmId);

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);


}

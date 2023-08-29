package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que define um repositório para entidades do tipo CropFertilizer.
 * Esta interface estende JpaRepository, fornecendo operações CRUD (Create, Read, Update, Delete).
 */
@Repository
public interface CropFertRepository extends JpaRepository<CropFertilizer, Long> {
  List<Fertilizer> findAllByCropId(Long cropId);
}

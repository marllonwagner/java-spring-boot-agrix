package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que define um repositório para entidades do tipo Fertilizer.
 * Esta interface estende JpaRepository, fornecendo operações CRUD (Create, Read, Update, Delete).
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}

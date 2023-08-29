package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que define um repositório para entidades do tipo Farm.
 * Esta interface estende JpaRepository, fornecendo operações CRUD (Create, Read, Update, Delete).
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}

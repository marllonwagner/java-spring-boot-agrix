package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para operações relacionadas à entidade Farm.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;


  /**
   * Construtor da classe FarmService.
   *
   * @param fertilizerRepository O repositório de fazendas.
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;

  }

  /**
   * Insere uma nova fazenda.
   *
   * @param fertilizer A fazenda a ser inserida.
   * @return A fazenda inserida.
   */
  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Optional<Fertilizer> getFertilizerById(Long id) {
    return fertilizerRepository.findById(id);
  }

}

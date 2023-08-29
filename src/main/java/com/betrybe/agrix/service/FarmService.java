package com.betrybe.agrix.service;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para operações relacionadas à entidade Farm.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;


  /**
   * Construtor da classe FarmService.
   *
   * @param farmRepository O repositório de fazendas.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;

  }

  /**
   * Insere uma nova fazenda.
   *
   * @param farm A fazenda a ser inserida.
   * @return A fazenda inserida.
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

}

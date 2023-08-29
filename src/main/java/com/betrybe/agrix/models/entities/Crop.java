package com.betrybe.agrix.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a crop.
 */
@Entity
@Table(name = "crops")
public class Crop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @Column(name = "planted_area")
  private Double plantedArea;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany
  @JoinTable(
      name = "crop_fertilizers",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private List<Fertilizer> fertilizers = new ArrayList<>();

  public Crop() {
    // Construtor vazio necessário para JPA
  }

  /**
   * Construtor da classe Crop.
   *
   * @param id          O identificador do cultivo.
   * @param name        O nome do cultivo.
   * @param plantedArea A área plantada do cultivo.
   */
  public Crop(Long id, String name, Double plantedArea,
      LocalDate plantedDate, LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  // Getters e Setters com Javadocs

  /**
   * Obtém o identificador do cultivo.
   *
   * @return O identificador do cultivo.
   */
  public Long getId() {
    return id;
  }

  /**
   * Define o identificador do cultivo.
   *
   * @param id O identificador do cultivo.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Obtém o nome do cultivo.
   *
   * @return O nome do cultivo.
   */
  public String getName() {
    return name;
  }

  /**
   * Define o nome do cultivo.
   *
   * @param name O nome do cultivo.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Obtém a área plantada do cultivo.
   *
   * @return A área plantada do cultivo.
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Define a área plantada do cultivo.
   *
   * @param plantedArea A área plantada do cultivo.
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Obtém a fazenda associada a este cultivo.
   *
   * @return A fazenda associada.
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Define a fazenda associada a este cultivo.
   *
   * @param farm A fazenda associada.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}

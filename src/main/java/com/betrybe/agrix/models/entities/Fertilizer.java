package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a fertilizer.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String brand;
  private String composition;

  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops = new ArrayList<>();

  /**
   * Constructor for the Fertilizer class.
   *
   * @param id          The identifier of the fertilizer.
   * @param name        The name of the fertilizer.
   * @param brand       The brand of the fertilizer.
   * @param composition The composition of the fertilizer.
   */
  public Fertilizer(Long id, String name, String brand, String composition) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }

  /**
   * Constructor default for jpa.
   */
  public Fertilizer() {
    // for jpa
  }

  /**
   * Get the identifier of the fertilizer.
   *
   * @return The identifier of the fertilizer.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the identifier of the fertilizer.
   *
   * @param id The identifier of the fertilizer.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the fertilizer.
   *
   * @return The name of the fertilizer.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the fertilizer.
   *
   * @param name The name of the fertilizer.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the brand of the fertilizer.
   *
   * @return The brand of the fertilizer.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Set the brand of the fertilizer.
   *
   * @param brand The brand of the fertilizer.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Get the composition of the fertilizer.
   *
   * @return The composition of the fertilizer.
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Set the composition of the fertilizer.
   *
   * @param composition The composition of the fertilizer.
   */
  public void setComposition(String composition) {
    this.composition = composition;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}


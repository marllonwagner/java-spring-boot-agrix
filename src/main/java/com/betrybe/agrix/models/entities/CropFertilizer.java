package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing the relationship between a Crop and a Fertilizer.
 */
@Entity
@Table(name = "crop_fertilizers")
public class CropFertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "crop_id")
  private Crop crop;

  @ManyToOne
  @JoinColumn(name = "fertilizer_id")
  private Fertilizer fertilizer;

  /**
   * Constructor for the CropFertilizer class.
   *
   * @param id         The identifier of the relationship.
   * @param crop       The associated Crop.
   * @param fertilizer The associated Fertilizer.
   */
  public CropFertilizer(Long id, Crop crop, Fertilizer fertilizer) {
    this.id = id;
    this.crop = crop;
    this.fertilizer = fertilizer;
  }

  public CropFertilizer() {

  }

  /**
   * Get the identifier of the relationship.
   *
   * @return The identifier of the relationship.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the identifier of the relationship.
   *
   * @param id The identifier of the relationship.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the associated Crop.
   *
   * @return The associated Crop.
   */
  public Crop getCrop() {
    return crop;
  }

  /**
   * Set the associated Crop.
   *
   * @param crop The associated Crop.
   */
  public void setCrop(Crop crop) {
    this.crop = crop;
  }

  /**
   * Get the associated Fertilizer.
   *
   * @return The associated Fertilizer.
   */
  public Fertilizer getFertilizer() {
    return fertilizer;
  }

  /**
   * Set the associated Fertilizer.
   *
   * @param fertilizer The associated Fertilizer.
   */
  public void setFertilizer(Fertilizer fertilizer) {
    this.fertilizer = fertilizer;
  }

}

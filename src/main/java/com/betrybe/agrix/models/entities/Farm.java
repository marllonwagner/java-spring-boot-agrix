package com.betrybe.agrix.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Entity representing a farm.
 */
@Entity
@Table(name = "farms")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;

  /**
   * Default constructor required for JPA.
   */
  public Farm() {
    // Default constructor required for JPA
  }

  /**
   * Constructor for the Farm class.
   *
   * @param id   The identifier of the farm.
   * @param name The name of the farm.
   * @param size The size of the farm.
   */
  public Farm(Long id, String name, Double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  // Getters and Setters with Javadocs

  /**
   * Get the identifier of the farm.
   *
   * @return The identifier of the farm.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the identifier of the farm.
   *
   * @param id The identifier of the farm.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the farm.
   *
   * @return The name of the farm.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the farm.
   *
   * @param name The name of the farm.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the size of the farm.
   *
   * @return The size of the farm.
   */
  public Double getSize() {
    return size;
  }

  /**
   * Set the size of the farm.
   *
   * @param size The size of the farm.
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * Get the list of crops associated with this farm.
   *
   * @return The list of associated crops.
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Set the list of crops associated with this farm.
   *
   * @param crops The list of associated crops.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}

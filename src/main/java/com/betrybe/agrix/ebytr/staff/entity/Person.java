package com.betrybe.agrix.ebytr.staff.entity;

import com.betrybe.agrix.ebytr.staff.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 * Class representing a person.
 */
@Entity
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  private Role role;

  /**
   * Default constructor.
   */
  public Person() {
  }

  /**
   * Parameterized constructor.
   *
   * @param id       The ID of the person.
   * @param username The username of the person.
   * @param password The password of the person.
   * @param role     The role of the person.
   */
  public Person(Long id, String username, String password, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
  }

  /**
   * Get the ID of the person.
   *
   * @return The ID of the person.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the ID of the person.
   *
   * @param id The ID to set for the person.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the username of the person.
   *
   * @return The username of the person.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set the username of the person.
   *
   * @param username The username to set for the person.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Get the password of the person.
   *
   * @return The password of the person.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the password of the person.
   *
   * @param password The password to set for the person.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get the role of the person.
   *
   * @return The role of the person.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Set the role of the person.
   *
   * @param role The role to set for the person.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Check if this person is equal to another object.
   *
   * @param o The object to compare to.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(username,
        person.username) && Objects.equals(password, person.password)
        && Objects.equals(role, person.role);
  }
}

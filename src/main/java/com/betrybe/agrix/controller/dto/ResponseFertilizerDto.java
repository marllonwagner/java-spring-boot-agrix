package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record ResponseFertilizerDto(Long id, String name, String brand, String composition) {

}
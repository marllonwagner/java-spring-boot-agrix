package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record ResponseCropDto(Long id, String name, Double plantedArea,
                              LocalDate plantedDate, LocalDate harvestDate, Long farmId) {

}
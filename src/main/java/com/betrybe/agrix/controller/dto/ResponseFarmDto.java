package com.betrybe.agrix.controller.dto;

/**
 * DTO (Data Transfer Object) que representa uma fazenda para fins de transferência de dados.
 */
public record ResponseFarmDto(Long id, String name, Double size) {

}
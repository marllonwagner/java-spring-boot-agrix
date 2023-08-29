package com.betrybe.agrix.controller.dto;

/**
 * DTO (Data Transfer Object) para encapsular uma resposta contendo uma mensagem e dados associados.
 *
 * @param <T> O tipo dos dados associados.
 */
public record ResponseDto<T>(String message, T data) {

}

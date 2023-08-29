package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.controller.dto.ResponseDto;
import com.betrybe.agrix.controller.dto.ResponseFertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for endpoints related to Fertilizer entity.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Constructor for the FertilizerController class.
   *
   * @param fertilizerService The fertilizer service.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Endpoint to insert a new fertilizer.
   *
   * @param fertilizerDto The DTO of the fertilizer to be inserted.
   * @return Response with the inserted fertilizer.
   */
  @PostMapping()
  public ResponseEntity insertFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFert = fertilizerService.insertFertilizer(fertilizerDto.toFertilizer());
    ResponseFertilizerDto responseFertilizerDto = new ResponseFertilizerDto(newFert.getId(),
        newFert.getName(), newFert.getBrand(), newFert.getComposition());
    ResponseDto<ResponseFertilizerDto> responseDto = new ResponseDto<>("Fertilizer inserted",
        responseFertilizerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.data());
  }

  /**
   * Endpoint to get the list of all fertilizers.
   *
   * @return List of DTOs representing fertilizers.
   */
  @GetMapping()
  @Secured("ADMIN")
  public List<FertilizerDto> getAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.getAllFertilizers();
    return allFertilizers.stream()
        .map(fert -> new FertilizerDto(fert.getId(), fert.getName(),
            fert.getBrand(), fert.getComposition()))
        .collect(Collectors.toList());
  }

  /**
   * Endpoint to get a fertilizer by its ID.
   *
   * @param fertilizerId The ID of the fertilizer.
   * @return DTO representing the fertilizer.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> optionalFert = fertilizerService.getFertilizerById(fertilizerId);

    if (optionalFert.isEmpty()) {
      ResponseDto<Fertilizer> responseDto = new ResponseDto<>(
          String.format("Fertilizante não encontrado!", fertilizerId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    ResponseFertilizerDto responseFertilizerDto = new ResponseFertilizerDto(
        optionalFert.get().getId(),
        optionalFert.get().getName(), optionalFert.get().getBrand(),
        optionalFert.get().getComposition());
    ResponseDto<ResponseFertilizerDto> responseDto = new ResponseDto<>("Fertilizer found ",
        responseFertilizerDto);
    return ResponseEntity.ok(responseDto.data());
  }
}

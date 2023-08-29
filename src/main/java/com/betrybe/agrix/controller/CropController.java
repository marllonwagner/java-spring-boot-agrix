package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.CropFertDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.controller.dto.ResponseCropDto;
import com.betrybe.agrix.controller.dto.ResponseDto;
import com.betrybe.agrix.controller.dto.ResponseFertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.CropFertilizer;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.CropFertService;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for endpoints related to crops within farms.
 */
@RestController
@RequestMapping(value = "crops")
public class CropController {

  private final CropService cropService;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  private final CropFertService cropFertService;


  /**
   * Constructor for the CropController class.
   *
   * @param cropService The crop service.
   * @param farmService The farm service.
   */
  public CropController(CropService cropService, FarmService farmService,
      FertilizerService fertilizerService, CropFertService cropFertService) {
    this.cropService = cropService;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
    this.cropFertService = cropFertService;
  }

  /**
   * Endpoint para obter a lista de todas as crops.
   *
   * @return Lista de DTOs representando as crops.
   */

  @GetMapping()
  @Secured({"MANAGER", "ADMIN"})
  public ResponseEntity getCropsByFarmId() {
    Optional<List<Crop>> crops = cropService.getAllCrops();


    if (crops.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Sem plantações!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    List<ResponseCropDto> responseDtoList = crops.get().stream()
        .map(crop -> new ResponseCropDto(crop.getId(),
            crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(responseDtoList);
  }

  /**
   * Endpoint para obter a crops.
   *
   * @return  DTOs representando as crops.
   */

  @GetMapping("/{id}")
  public ResponseEntity getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      ResponseDto<Crop> responseDto = new ResponseDto<>(
          String.format("Plantação não encontrada!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }
    ResponseCropDto responseCropDto = new ResponseCropDto(optionalCrop.get().getId(),
        optionalCrop.get().getName(), optionalCrop.get().getPlantedArea(),
        optionalCrop.get().getPlantedDate(), optionalCrop.get().getHarvestDate(),
        optionalCrop.get().getFarm().getId());
    ResponseDto<ResponseCropDto> responseDto = new ResponseDto<>("Crop encontrada com sucesso!",
        responseCropDto);
    return ResponseEntity.ok(responseDto.data());
  }

  /**
   * Endpoint para obter as crops por data.
   *
   * @return  DTOs representando as crops.
   */

  @GetMapping("/search")
  public ResponseEntity getCropsByDateRange(
      @RequestParam("start") String start,
      @RequestParam("end") String end) {

    start = start.trim(); // Remove leading and trailing whitespace
    end = end.trim();

    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);

    List<Crop> cropsInRange = cropService.getCropsBetweenDates(startDate, endDate);

    if (cropsInRange.isEmpty()) {
      ResponseDto<String> responseDto = new ResponseDto<>(
          String.format("Nenhuma plantação encontrada no intervalo especificado!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    List<ResponseCropDto> responseDtoList = cropsInRange.stream()
        .map(crop -> new ResponseCropDto(crop.getId(),
            crop.getName(), crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate(),
            crop.getFarm().getId()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(responseDtoList);
  }

  /**
   * Endpoint para associar crops aos fertilizantes.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity insertFertilizerInCrop(@PathVariable Long fertilizerId,
      @PathVariable Long cropId) {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(fertilizerId);

    if (optionalCrop.isEmpty()) {
      ResponseDto responseDto = new ResponseDto<>(
          String.format("Plantação não encontrada!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }
    if (optionalFertilizer.isEmpty()) {
      ResponseDto responseDto = new ResponseDto<>(
          String.format("Fertilizante não encontrado!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    CropFertDto cropFertDto = new CropFertDto(crop, fertilizer);

    cropFertService.insertCropFert(cropFertDto.toCropFert());
    ResponseDto<ResponseCropDto> responseDto = new ResponseDto<>(
        "Fertilizante e plantação associados com sucesso!", null
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.message());
  }

  /**
   * Endpoint para encontrar fertilizantes de crops por id.
   */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity getFertilizersByCropId(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);

    if (optionalCrop.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Plantação não encontrada!"), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    Crop crop = optionalCrop.get();
    List<Fertilizer> fertilizersList = crop.getFertilizers();

    List<ResponseFertilizerDto> responseDtoList = fertilizersList.stream()
        .map(fertilizer -> new ResponseFertilizerDto(fertilizer.getId(),
            fertilizer.getName(), fertilizer.getBrand(),
            fertilizer.getComposition()))
        .collect(Collectors.toList());

    return ResponseEntity.ok(responseDtoList);
  }


}

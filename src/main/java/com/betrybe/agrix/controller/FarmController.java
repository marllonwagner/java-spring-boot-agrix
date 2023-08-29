package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.controller.dto.ResponseCropDto;
import com.betrybe.agrix.controller.dto.ResponseDto;
import com.betrybe.agrix.controller.dto.ResponseFarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
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
 * Controlador responsável por endpoints relacionados à entidade Farm.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Construtor da classe FarmController.
   *
   * @param farmService O serviço de fazendas.
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Endpoint para inserir uma nova fazenda.
   *
   * @param farmDto O DTO da fazenda a ser inserida.
   * @return Resposta com a fazenda inserida.
   */
  @PostMapping()
  @Secured({"USER", "MANAGER", "ADMIN"})
  public ResponseEntity insertFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    ResponseFarmDto responseFarmDto = new ResponseFarmDto(newFarm.getId(),
        newFarm.getName(), newFarm.getSize());
    ResponseDto<ResponseFarmDto> responseDto = new ResponseDto<>("Fazenda inserida com sucesso!",
        responseFarmDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.data());
  }

  /**
   * Endpoint para obter a lista de todas as fazendas.
   *
   * @return Lista de DTOs representando as fazendas.
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getSize(), farm.getName()))
        .collect(Collectors.toList());
  }
  /**
   * Endpoint para obter as fazendas por id.
   *
   * @return DTO representando a fazenda.
   */

  @GetMapping("/{farmId}")
  public ResponseEntity getFarmById(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Fazenda não encontrada!", farmId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }
    ResponseFarmDto responseFarmDto = new ResponseFarmDto(optionalFarm.get().getId(),
        optionalFarm.get().getName(), optionalFarm.get().getSize());
    ResponseDto<ResponseFarmDto> responseDto = new ResponseDto<>("Fazenda encontrada com sucesso!",
        responseFarmDto);
    return ResponseEntity.ok(responseDto.data());
  }

  /**
   * Inserts a new crop associated with a farm.
   *
   * @param farmId   The ID of the farm to which the crop will be associated.
   * @param cropDto  The DTO with the crop data to be inserted.
   * @return The response with details of the inserted crop.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity insertCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Fazenda não encontrada!", farmId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    Crop newCrop = cropService.insertCrop(cropDto.toCrop(farmId));
    ResponseCropDto responseCropDto = new ResponseCropDto(newCrop.getId(), newCrop.getName(),
        newCrop.getPlantedArea(), newCrop.getPlantedDate(), newCrop.getHarvestDate(), farmId);
    ResponseDto<ResponseCropDto> responseDto = new ResponseDto<>("Plantação inserida com sucesso!",
        responseCropDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.data());
  }

  /**
   * Inserts a new crop associated with a farm.
   *
   * @param farmId   The ID of the farm to which the crop will be associated.
   * @return The response with details of the inserted crop.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity getCropsByFarmId(@PathVariable Long farmId) {
    Optional<List<Crop>> crops = cropService.getCropsByFarmId(farmId);
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Fazenda não encontrada!", farmId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    if (crops.isEmpty()) {
      ResponseDto<Farm> responseDto = new ResponseDto<>(
          String.format("Fazenda sem plantações!", farmId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto.message());
    }

    List<ResponseCropDto> responseDtoList = crops.get().stream()
        .map(crop -> new ResponseCropDto(crop.getId(),
            crop.getName(), crop.getPlantedArea(),
            crop.getPlantedDate(), crop.getHarvestDate(), farmId))
        .collect(Collectors.toList());

    return ResponseEntity.ok(responseDtoList);
  }
}

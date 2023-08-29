package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.controller.dto.ResponseDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.ResponsePersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling Person-related operations.
 */

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Endpoint for inserting a new person.
   *
   * @param personDto The PersonDto containing the data for the new person.
   * @return A ResponseEntity containing the response data.
   */

  @PostMapping()
  public ResponseEntity insertPerson(@RequestBody PersonDto personDto) {
    Person newPerson = personService.create(personDto.toPerson());
    ResponsePersonDto responsePersonDto = new ResponsePersonDto(newPerson.getId(),
        newPerson.getUsername(), newPerson.getRole());
    ResponseDto<ResponsePersonDto> responseDto = new ResponseDto<>("Pessoa inserida com sucesso!",
        responsePersonDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto.data());
  }
}

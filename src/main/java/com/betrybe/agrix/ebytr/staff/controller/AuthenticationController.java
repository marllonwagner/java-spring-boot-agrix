package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.controller.dto.ResponseDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.AuthenticationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.TokenResponseDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication operations.
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  /**
   * Constructor.
   */

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      PersonService personService, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.tokenService = tokenService;
  }

  /**
   * Handles user authentication and provides a token on success.
   *
   * @param authenticationDto The DTO containing authentication credentials.
   * @return ResponseEntity containing the token or an error message.
   */

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody AuthenticationDto authenticationDto) {
    try {
      UsernamePasswordAuthenticationToken usernamePassword =
          new UsernamePasswordAuthenticationToken(authenticationDto.username(),
              authenticationDto.password());
      Authentication auth = authenticationManager.authenticate(usernamePassword);
      Person person = (Person) auth.getPrincipal();

      String token = tokenService.generateToken(person);

      TokenResponseDto tokenResponsedto = new TokenResponseDto(token);
      return ResponseEntity.status(HttpStatus.OK).body(tokenResponsedto);

    } catch (BadCredentialsException e) {
      ResponseDto<String> response = new ResponseDto<>(
          "Falha na autenticação: Credenciais inválidas.", null
      );
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response.message());
    }
  }


}
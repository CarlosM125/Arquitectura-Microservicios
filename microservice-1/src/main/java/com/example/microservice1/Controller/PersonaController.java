package com.example.microservice1.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.microservice1.Model.Persona;
import com.example.microservice1.Service.PersonaService;

@RestController
@RequestMapping("/personas")
@RequiredArgsConstructor
public class PersonaController {

  private final PersonaService personaService;

  @PostMapping
  public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
      Persona nuevaPersona = personaService.guardarPersona(persona);
      return ResponseEntity.status(201).body(nuevaPersona);
  }

}


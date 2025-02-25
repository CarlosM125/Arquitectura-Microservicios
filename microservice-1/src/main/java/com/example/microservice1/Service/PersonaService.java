package com.example.microservice1.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.microservice1.Model.Persona;
import com.example.microservice1.Repository.PersonaRepository;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Transactional
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }
}


package com.example.microservice1.Service;

import org.springframework.stereotype.Service;

import com.example.microservice1.Model.Cliente;
import com.example.microservice1.Model.Persona;
import com.example.microservice1.Repository.ClienteRepository;
import com.example.microservice1.Repository.PersonaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;


    @Transactional
    public Cliente guardarCliente(Integer personaId, String password, Boolean estado) {
        Persona personaExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + personaId));

        Cliente cliente = Cliente.builder()
                .persona(personaExistente)
                .password(password)
                .estado(estado)
                .build();

        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}


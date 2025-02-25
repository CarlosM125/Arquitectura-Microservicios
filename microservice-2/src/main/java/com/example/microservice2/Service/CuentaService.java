package com.example.microservice2.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.microservice2.Controller.Dto.ClienteDTO;
import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Repository.CuentaRepository;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final RestTemplate restTemplate; 
    /**
     * Guarda una cuenta en la base de datos.
     */
   @Transactional
    public Cuenta crearCuenta(Integer clienteId, String tipoCuenta, Double saldoInicial, Boolean estado) {
        String url = "http://localhost:8082/clientes/" + clienteId;

        ClienteDTO cliente;
        try {
            ResponseEntity<ClienteDTO> response = restTemplate.getForEntity(url, ClienteDTO.class);
            cliente = response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Cliente no encontrado en el Microservicio 1");
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con el Microservicio 1");
        }

        if (cliente == null) {
            throw new RuntimeException("Cliente no encontrado en el Microservicio 1");
        }

        Cuenta cuenta = Cuenta.builder()
                .clienteId(clienteId)
                .tipoCuenta(tipoCuenta)
                .saldoInicial(saldoInicial)
                .estado(estado)
                .build();

        return cuentaRepository.save(cuenta);
    }
    
    
    /**
     * Obtiene una cuenta por su número de cuenta.
     */
    public Cuenta obtenerCuentaPorId(Integer numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    /**
     * Obtiene todas las cuentas asociadas a un cliente.
     */
    public List<Cuenta> obtenerCuentasPorCliente(Integer clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}


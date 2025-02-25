package com.example.microservice2.Service;
import com.example.microservice2.Controller.Dto.ClienteDTO;
import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Repository.CuentaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteEventListener {

    private final ObjectMapper objectMapper;
    private final CuentaRepository cuentaRepository;

    @RabbitListener(queues = "clienteQueue")
    public void recibirClienteCreado(String mensaje) {
        try {
            ClienteDTO cliente = objectMapper.readValue(mensaje, ClienteDTO.class);
            System.out.println("Nuevo cliente recibido: " + cliente);

            // Crear cuenta por defecto para el nuevo cliente
            Cuenta nuevaCuenta = Cuenta.builder()
                .clienteId(cliente.getClienteId())
                .tipoCuenta("Ahorros")
                .saldoInicial(0.0)
                .estado(true)
                .build();

            cuentaRepository.save(nuevaCuenta);
            System.out.println("Cuenta creada para cliente ID: " + cliente.getClienteId());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}


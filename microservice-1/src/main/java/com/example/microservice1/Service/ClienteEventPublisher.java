package com.example.microservice1.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.microservice1.Model.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String EXCHANGE_NAME = "clienteExchange";

    public void enviarClienteCreado(Cliente cliente) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "cliente.creado", cliente);
    }
}

package com.example.microservice1.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue clienteQueue() {
        return QueueBuilder.durable("clienteQueue").build();
    }

    @Bean
    public DirectExchange clienteExchange() {
        return new DirectExchange("clienteExchange");
    }

    @Bean
    public Binding binding(Queue clienteQueue, DirectExchange clienteExchange) {
        return BindingBuilder.bind(clienteQueue).to(clienteExchange).with("cliente.creado");
    }
}


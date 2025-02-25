package com.example.microservice2.Controller.Dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer clienteId;
    private String nombre;
    private String identificacion;
    private Boolean estado;
}


package com.example.microservice2.Controller.Dto;

import java.util.List;

import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Model.Movimiento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCuentaDTO {
    private List<Cuenta> cuentas;
    private List<Movimiento> movimientos;
}
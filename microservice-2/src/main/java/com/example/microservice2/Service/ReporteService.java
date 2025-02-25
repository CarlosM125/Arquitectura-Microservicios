package com.example.microservice2.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.microservice2.Controller.Dto.EstadoCuentaDTO;
import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Model.Movimiento;
import com.example.microservice2.Repository.CuentaRepository;
import com.example.microservice2.Repository.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public EstadoCuentaDTO generarReporte(Integer clienteId) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<Movimiento> movimientos = cuentas.stream()
                .flatMap(cuenta -> movimientoRepository.findByCuenta_NumeroCuenta(cuenta.getNumeroCuenta()).stream())
                .collect(Collectors.toList());

        return new EstadoCuentaDTO(cuentas, movimientos);
    }
}


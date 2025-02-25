package com.example.microservice2.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Model.Movimiento;
import com.example.microservice2.Repository.CuentaRepository;
import com.example.microservice2.Repository.MovimientoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Transactional
    public Movimiento registrarMovimiento(Integer numeroCuenta, String tipoMovimiento, Double valor) {
        // 1️⃣ Verificar que la Cuenta existe
        Cuenta cuenta = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        // 2️⃣ Validar saldo para retiros
        Double nuevoSaldo = cuenta.getSaldoInicial();
        if (tipoMovimiento.equalsIgnoreCase("Retiro")) {
            if (nuevoSaldo < valor) {
                throw new RuntimeException("Saldo no disponible");
            }
            nuevoSaldo -= valor;
        } else if (tipoMovimiento.equalsIgnoreCase("Deposito")) {
            nuevoSaldo += valor;
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido");
        }

        // 3️⃣ Guardar el Movimiento
        Movimiento movimiento = Movimiento.builder()
                .cuenta(cuenta)
                .fecha(LocalDateTime.now())
                .tipoMovimiento(tipoMovimiento)
                .valor(valor)
                .saldo(nuevoSaldo)
                .build();

        movimientoRepository.save(movimiento);

        // 4️⃣ Actualizar el saldo de la cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        return movimiento;
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(Integer numeroCuenta) {
        return movimientoRepository.findByCuenta_NumeroCuenta(numeroCuenta);
    }

    public List<Movimiento> obtenerMovimientosPorClienteYRangoDeFechas(Integer clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoRepository.findByCuenta_ClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);
    }
}


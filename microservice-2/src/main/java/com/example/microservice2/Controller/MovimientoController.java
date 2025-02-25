package com.example.microservice2.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.microservice2.Model.Movimiento;
import com.example.microservice2.Service.MovimientoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestParam Integer numeroCuenta,
                                                          @RequestParam String tipoMovimiento,
                                                          @RequestParam Double valor) {
        Movimiento movimiento = movimientoService.registrarMovimiento(numeroCuenta, tipoMovimiento, valor);
        return ResponseEntity.status(201).body(movimiento);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable Integer numeroCuenta) {
        return ResponseEntity.ok(movimientoService.obtenerMovimientosPorCuenta(numeroCuenta));
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorClienteYRangoDeFechas(
            @RequestParam Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        LocalDateTime inicio = LocalDateTime.parse(fechaInicio + "T00:00:00");
        LocalDateTime fin = LocalDateTime.parse(fechaFin + "T23:59:59");

        return ResponseEntity.ok(movimientoService.obtenerMovimientosPorClienteYRangoDeFechas(clienteId, inicio, fin));
    }
}

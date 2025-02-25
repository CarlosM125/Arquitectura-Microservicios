package com.example.microservice2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice2.Model.Cuenta;
import com.example.microservice2.Service.CuentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

     @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestParam Integer clienteId,
                                              @RequestParam String tipoCuenta,
                                              @RequestParam Double saldoInicial,
                                              @RequestParam Boolean estado) {
        Cuenta nuevaCuenta = cuentaService.crearCuenta(clienteId, tipoCuenta, saldoInicial, estado);
        return ResponseEntity.status(201).body(nuevaCuenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Integer id) {
        return ResponseEntity.ok(cuentaService.obtenerCuentaPorId(id));
    }
}


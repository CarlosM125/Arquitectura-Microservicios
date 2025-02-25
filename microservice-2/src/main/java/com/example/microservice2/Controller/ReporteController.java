package com.example.microservice2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice2.Controller.Dto.EstadoCuentaDTO;
import com.example.microservice2.Service.ReporteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<EstadoCuentaDTO> obtenerReporte(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(reporteService.generarReporte(clienteId));
    }
}


package com.example.microservice2.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservice2.Model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
     List<Movimiento> findByCuenta_NumeroCuenta(Integer numeroCuenta);

    List<Movimiento> findByCuenta_ClienteIdAndFechaBetween(
            Integer clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin
    );
}


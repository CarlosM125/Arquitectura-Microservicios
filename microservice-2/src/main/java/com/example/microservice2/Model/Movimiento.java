package com.example.microservice2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MOVIMIENTO")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIMIENTO")
    private Integer idMovimiento;

    @ManyToOne
    @JoinColumn(name = "NUMERO_CUENTA", nullable = false)
    private Cuenta cuenta;

    @Column(name = "FECHA", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "TIPO_MOVIMIENTO", length = 20, nullable = false)
    private String tipoMovimiento;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;
}

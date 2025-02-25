package com.example.microservice2.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CUENTA")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO_CUENTA")
    private Integer numeroCuenta;

    @Column(name = "CLIENTE_ID", nullable = false)
    private Integer clienteId; 

    @Column(name = "TIPO_CUENTA", length = 20, nullable = false)
    private String tipoCuenta;

    @Column(name = "SALDO_INICIAL", nullable = false)
    private Double saldoInicial;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado;
}




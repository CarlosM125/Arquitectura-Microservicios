package com.example.microservice1.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENTE_ID")
    private Integer clienteId;

    @OneToOne
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID", nullable = false, unique = true)
    private Persona persona;

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @Column(name = "ESTADO", nullable = false)
    private Boolean estado;
}


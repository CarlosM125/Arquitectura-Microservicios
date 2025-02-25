package com.example.microservice1.Model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE", length = 70, nullable = false)
    private String nombre;

    @Column(name = "GENERO", length = 10)
    private String genero;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "IDENTIFICACION", unique = true, nullable = false)
    private String identificacion;

    @Column(name = "DIRECCION", length = 100)
    private String direccion;

    @Column(name = "TELEFONO", length = 30, nullable = false)
    private String telefono;

    public static Persona guardarPersona(Persona persona) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'guardarPersona'");
    }
}

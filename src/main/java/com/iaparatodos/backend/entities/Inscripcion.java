package com.iaparatodos.backend.entities;

import com.iaparatodos.backend.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String codigoArea;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provincia provincia;

    @Enumerated(EnumType.STRING)
    @Column
    private BarrioCaba barrioCaba;

    @Enumerated(EnumType.STRING)
    @Column
    private LocalidadAmba localidadAmba;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelEducativo nivelEducativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacionActual situacionActual;

    @Column(nullable = false)
    private Boolean tieneComputadora;

    @Column(nullable = false)
    private Boolean tieneInternet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelDigital nivelDigital;

    @Column(nullable = false)
    private Boolean usoIaAntes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Motivacion motivacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComoSeEntero comoSeEntero;

    @Column(nullable = false)
    private Boolean participoAgencia;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    @PrePersist
    protected void onCreate() {
        this.fechaInscripcion = LocalDateTime.now();
    }
}
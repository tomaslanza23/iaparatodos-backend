package com.iaparatodos.backend.dto.response;

import com.iaparatodos.backend.enums.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String codigoArea;
    private String celular;
    private LocalDate fechaNacimiento;
    private Genero genero;
    private Provincia provincia;
    private BarrioCaba barrioCaba;
    private LocalidadAmba localidadAmba;
    private NivelEducativo nivelEducativo;
    private SituacionActual situacionActual;
    private Boolean tieneComputadora;
    private Boolean tieneInternet;
    private NivelDigital nivelDigital;
    private Boolean usoIaAntes;
    private Motivacion motivacion;
    private ComoSeEntero comoSeEntero;
    private Boolean participoAgencia;
    private LocalDateTime fechaInscripcion;
}
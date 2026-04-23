package com.iaparatodos.backend.dto.request;

import com.iaparatodos.backend.enums.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener 7 u 8 dígitos numéricos")
    private String dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotBlank(message = "La confirmación de email es obligatoria")
    @Email(message = "La confirmación de email no tiene un formato válido")
    private String confirmacionEmail;

    @NotBlank(message = "El código de área es obligatorio")
    private String codigoArea;

    @NotBlank(message = "El celular es obligatorio")
    private String celular;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El género es obligatorio")
    private Genero genero;

    @NotNull(message = "La provincia es obligatoria")
    private Provincia provincia;

    private BarrioCaba barrioCaba;

    private LocalidadAmba localidadAmba;

    @NotNull(message = "El nivel educativo es obligatorio")
    private NivelEducativo nivelEducativo;

    @NotNull(message = "La situación actual es obligatoria")
    private SituacionActual situacionActual;

    @NotNull(message = "Indicá si tenés computadora")
    private Boolean tieneComputadora;

    @NotNull(message = "Indicá si tenés acceso a internet")
    private Boolean tieneInternet;

    @NotNull(message = "El nivel de manejo digital es obligatorio")
    private NivelDigital nivelDigital;

    @NotNull(message = "Indicá si usaste IA antes")
    private Boolean usoIaAntes;

    @NotNull(message = "La motivación es obligatoria")
    private Motivacion motivacion;

    @NotNull(message = "Indicá cómo te enteraste")
    private ComoSeEntero comoSeEntero;

    @NotNull(message = "Indicá si participaste anteriormente")
    private Boolean participoAgencia;
}
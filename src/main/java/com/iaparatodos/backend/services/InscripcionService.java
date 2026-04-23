package com.iaparatodos.backend.services;

import com.iaparatodos.backend.dto.request.InscripcionRequest;
import com.iaparatodos.backend.dto.response.InscripcionResponse;
import com.iaparatodos.backend.entities.Inscripcion;
import com.iaparatodos.backend.enums.Provincia;
import com.iaparatodos.backend.repositories.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    @Transactional
    public InscripcionResponse inscribir(InscripcionRequest request) {

        // Validación: emails coinciden
        if (!request.getEmail().equalsIgnoreCase(request.getConfirmacionEmail())) {
            throw new IllegalArgumentException("El email y la confirmación no coinciden");
        }

        // Validación: DNI duplicado
        if (inscripcionRepository.existsByDni(request.getDni())) {
            throw new IllegalArgumentException("Ya existe una inscripción con ese DNI");
        }

        // Validación: email duplicado
        if (inscripcionRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe una inscripción con ese email");
        }

        // Validación: barrio requerido si CABA
        if (request.getProvincia() == Provincia.CABA && request.getBarrioCaba() == null) {
            throw new IllegalArgumentException("Seleccioná el barrio de CABA");
        }

        // Validación: localidad requerida si Buenos Aires
        if (request.getProvincia() == Provincia.BUENOS_AIRES && request.getLocalidadAmba() == null) {
            throw new IllegalArgumentException("Seleccioná la localidad del AMBA");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setNombre(request.getNombre().trim());
        inscripcion.setApellido(request.getApellido().trim());
        inscripcion.setDni(request.getDni().trim());
        inscripcion.setEmail(request.getEmail().trim().toLowerCase());
        inscripcion.setCodigoArea(request.getCodigoArea().trim());
        inscripcion.setCelular(request.getCelular().trim());
        inscripcion.setFechaNacimiento(request.getFechaNacimiento());
        inscripcion.setGenero(request.getGenero());
        inscripcion.setProvincia(request.getProvincia());
        inscripcion.setBarrioCaba(request.getBarrioCaba());
        inscripcion.setLocalidadAmba(request.getLocalidadAmba());
        inscripcion.setNivelEducativo(request.getNivelEducativo());
        inscripcion.setSituacionActual(request.getSituacionActual());
        inscripcion.setTieneComputadora(request.getTieneComputadora());
        inscripcion.setTieneInternet(request.getTieneInternet());
        inscripcion.setNivelDigital(request.getNivelDigital());
        inscripcion.setUsoIaAntes(request.getUsoIaAntes());
        inscripcion.setMotivacion(request.getMotivacion());
        inscripcion.setComoSeEntero(request.getComoSeEntero());
        inscripcion.setParticipoAgencia(request.getParticipoAgencia());

        Inscripcion guardada = inscripcionRepository.save(inscripcion);

        return toResponse(guardada);
    }

    private InscripcionResponse toResponse(Inscripcion i) {
        return InscripcionResponse.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellido(i.getApellido())
                .dni(i.getDni())
                .email(i.getEmail())
                .codigoArea(i.getCodigoArea())
                .celular(i.getCelular())
                .fechaNacimiento(i.getFechaNacimiento())
                .genero(i.getGenero())
                .provincia(i.getProvincia())
                .barrioCaba(i.getBarrioCaba())
                .localidadAmba(i.getLocalidadAmba())
                .nivelEducativo(i.getNivelEducativo())
                .situacionActual(i.getSituacionActual())
                .tieneComputadora(i.getTieneComputadora())
                .tieneInternet(i.getTieneInternet())
                .nivelDigital(i.getNivelDigital())
                .usoIaAntes(i.getUsoIaAntes())
                .motivacion(i.getMotivacion())
                .comoSeEntero(i.getComoSeEntero())
                .participoAgencia(i.getParticipoAgencia())
                .fechaInscripcion(i.getFechaInscripcion())
                .build();
    }
}
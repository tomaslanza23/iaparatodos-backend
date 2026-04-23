package com.iaparatodos.backend.repositories;

import com.iaparatodos.backend.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    Slice<Inscripcion> findAllBy(Pageable pageable);
}
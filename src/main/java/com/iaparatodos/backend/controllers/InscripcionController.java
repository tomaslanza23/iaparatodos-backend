package com.iaparatodos.backend.controllers;

import com.iaparatodos.backend.dto.request.InscripcionRequest;
import com.iaparatodos.backend.dto.response.InscripcionResponse;
import com.iaparatodos.backend.services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping("/inscripciones")
    public ResponseEntity<InscripcionResponse> inscribir(
            @Valid @RequestBody InscripcionRequest request) {
        InscripcionResponse response = inscripcionService.inscribir(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
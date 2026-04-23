package com.iaparatodos.backend.services;

import com.iaparatodos.backend.entities.Inscripcion;
import com.iaparatodos.backend.repositories.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ExportService {

    private final InscripcionRepository inscripcionRepository;

    private static final int PAGE_SIZE    = 1000;
    private static final int ROW_WINDOW   = 100;

    private static final String[] HEADERS = {
            "ID", "Nombre", "Apellido", "DNI", "Email",
            "Código de área", "Celular", "Fecha de nacimiento", "Género",
            "Provincia", "Barrio CABA", "Localidad AMBA",
            "Nivel educativo", "Situación actual",
            "Tiene computadora", "Tiene internet",
            "Nivel digital", "Usó IA antes",
            "Motivación", "Cómo se enteró",
            "Participó en Agencia", "Fecha de inscripción"
    };

    @Transactional(readOnly = true)
    public byte[] exportarInscripciones() throws IOException {

        SXSSFWorkbook workbook = new SXSSFWorkbook(ROW_WINDOW);
        workbook.setCompressTempFiles(true);

        try {
            Sheet sheet = workbook.createSheet("Inscripciones");

            // Encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADERS[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos paginados
            int rowNum = 1;
            int page   = 0;
            Slice<Inscripcion> slice;

            do {
                Pageable pageable = PageRequest.of(page, PAGE_SIZE);
                slice = inscripcionRepository.findAllBy(pageable);

                for (Inscripcion i : slice.getContent()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(i.getId());
                    row.createCell(1).setCellValue(i.getNombre());
                    row.createCell(2).setCellValue(i.getApellido());
                    row.createCell(3).setCellValue(i.getDni());
                    row.createCell(4).setCellValue(i.getEmail());
                    row.createCell(5).setCellValue(i.getCodigoArea());
                    row.createCell(6).setCellValue(i.getCelular());
                    row.createCell(7).setCellValue(i.getFechaNacimiento() != null ? i.getFechaNacimiento().toString() : "");
                    row.createCell(8).setCellValue(i.getGenero() != null ? i.getGenero().name() : "");
                    row.createCell(9).setCellValue(i.getProvincia() != null ? i.getProvincia().name() : "");
                    row.createCell(10).setCellValue(i.getBarrioCaba() != null ? i.getBarrioCaba().name() : "");
                    row.createCell(11).setCellValue(i.getLocalidadAmba() != null ? i.getLocalidadAmba().name() : "");
                    row.createCell(12).setCellValue(i.getNivelEducativo() != null ? i.getNivelEducativo().name() : "");
                    row.createCell(13).setCellValue(i.getSituacionActual() != null ? i.getSituacionActual().name() : "");
                    row.createCell(14).setCellValue(i.getTieneComputadora() != null ? (i.getTieneComputadora() ? "Sí" : "No") : "");
                    row.createCell(15).setCellValue(i.getTieneInternet() != null ? (i.getTieneInternet() ? "Sí" : "No") : "");
                    row.createCell(16).setCellValue(i.getNivelDigital() != null ? i.getNivelDigital().name() : "");
                    row.createCell(17).setCellValue(i.getUsoIaAntes() != null ? (i.getUsoIaAntes() ? "Sí" : "No") : "");
                    row.createCell(18).setCellValue(i.getMotivacion() != null ? i.getMotivacion().name() : "");
                    row.createCell(19).setCellValue(i.getComoSeEntero() != null ? i.getComoSeEntero().name() : "");
                    row.createCell(20).setCellValue(i.getParticipoAgencia() != null ? (i.getParticipoAgencia() ? "Sí" : "No") : "");
                    row.createCell(21).setCellValue(i.getFechaInscripcion() != null ? i.getFechaInscripcion().toString() : "");
                }

                page++;

            } while (slice.hasNext());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();

        } finally {
            workbook.dispose(); // elimina archivos temporales en disco
            workbook.close();
        }
    }
}
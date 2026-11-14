package com.example.demo.controllers;

import com.example.demo.dtos.ExperienciaRequestDto;
import com.example.demo.dtos.ExperienciaResponseDto;
import com.example.demo.services.ExperienciaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(experienciaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ExperienciaResponseDto> crear(
            @RequestBody ExperienciaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(experienciaService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody ExperienciaRequestDto dto) {
        return ResponseEntity.ok(experienciaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        experienciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.demo.controllers;

import com.example.demo.dtos.ProfesorRequestDto;
import com.example.demo.dtos.ProfesorResponseDto;
import com.example.demo.services.ProfesorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(profesorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProfesorResponseDto> crear(
            @RequestBody ProfesorRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(profesorService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody ProfesorRequestDto dto) {
        return ResponseEntity.ok(profesorService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
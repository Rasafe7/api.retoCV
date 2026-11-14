package com.example.demo.controllers;

import com.example.demo.dtos.CurriculumRequestDto;
import com.example.demo.dtos.CurriculumResponseDto;
import com.example.demo.services.CurriculumService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curriculums")
public class CurriculumController {

    private final CurriculumService curriculumService;

    public CurriculumController(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @GetMapping
    public ResponseEntity<List<CurriculumResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(curriculumService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(curriculumService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CurriculumResponseDto> crear(
            @RequestBody CurriculumRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(curriculumService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurriculumResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody CurriculumRequestDto dto) {
        return ResponseEntity.ok(curriculumService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        curriculumService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.demo.controllers;

import com.example.demo.dtos.AptitudRequestDto;
import com.example.demo.dtos.AptitudResponseDto;
import com.example.demo.services.AptitudService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aptitudes")
public class AptitudController {

    private final AptitudService aptitudService;

    public AptitudController(AptitudService aptitudService) {
        this.aptitudService = aptitudService;
    }

    @GetMapping
    public ResponseEntity<List<AptitudResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(aptitudService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AptitudResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(aptitudService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<AptitudResponseDto> crear(@RequestBody AptitudRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aptitudService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AptitudResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody AptitudRequestDto dto) {
        return ResponseEntity.ok(aptitudService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        aptitudService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
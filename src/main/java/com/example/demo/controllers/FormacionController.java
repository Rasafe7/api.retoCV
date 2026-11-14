package com.example.demo.controllers;

import com.example.demo.dtos.FormacionRequestDto;
import com.example.demo.dtos.FormacionResponseDto;
import com.example.demo.services.FormacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formaciones")
public class FormacionController {

    private final FormacionService formacionService;

    public FormacionController(FormacionService formacionService) {
        this.formacionService = formacionService;
    }

    @GetMapping
    public ResponseEntity<List<FormacionResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(formacionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormacionResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(formacionService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<FormacionResponseDto> crear(
            @RequestBody FormacionRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(formacionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormacionResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody FormacionRequestDto dto) {
        return ResponseEntity.ok(formacionService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        formacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
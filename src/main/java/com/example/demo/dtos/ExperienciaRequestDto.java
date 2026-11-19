package com.example.demo.dtos;

public record ExperienciaRequestDto(
    String nombre,
    String descripcion,
    Long idCurriculum
) {
}
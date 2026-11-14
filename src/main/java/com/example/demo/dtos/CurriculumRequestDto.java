package com.example.demo.dtos;

public record CurriculumRequestDto(
    Long idAlumno,
    Long idAptitud,
    Long idExperiencia,
    Long idFormacion
) {
}
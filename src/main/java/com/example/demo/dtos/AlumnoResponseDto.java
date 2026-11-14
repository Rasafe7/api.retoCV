package com.example.demo.dtos;

public record AlumnoResponseDto(
    Long idAlumno,
    String nombre,
    String apellidos,
    String dni,
    String correo
) {
}
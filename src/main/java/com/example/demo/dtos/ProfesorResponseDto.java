package com.example.demo.dtos;

public record ProfesorResponseDto(
    Long idProfesor,
    String nombre,
    String apellidos,
    String dni,
    String correo
) {
}
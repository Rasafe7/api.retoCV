package com.example.demo.dtos;

public record AlumnoRequestDto(
    String nombre,
    String apellidos,
    String dni,
    String correo
) {
}
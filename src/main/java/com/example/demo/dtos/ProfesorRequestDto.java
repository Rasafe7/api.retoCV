package com.example.demo.dtos;

public record ProfesorRequestDto(
    String nombre,
    String apellidos,
    String dni,
    String correo
) {
}
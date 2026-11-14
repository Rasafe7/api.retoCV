package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.AlumnoRequestDto;
import com.example.demo.dtos.AlumnoResponseDto;

public interface AlumnoService {
 AlumnoResponseDto crear(AlumnoRequestDto dto);

    List<AlumnoResponseDto> obtenerTodos();

    AlumnoResponseDto obtenerPorId(Long id);

    AlumnoResponseDto actualizar(Long id, AlumnoRequestDto dto);

    void eliminar(Long id);
}

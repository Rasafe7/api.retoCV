package com.example.demo.services;

import com.example.demo.dtos.ExperienciaRequestDto;
import com.example.demo.dtos.ExperienciaResponseDto;
import java.util.List;

public interface ExperienciaService {

    ExperienciaResponseDto crear(ExperienciaRequestDto dto);

    List<ExperienciaResponseDto> obtenerTodos();

    ExperienciaResponseDto obtenerPorId(Long id);

    ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto dto);

    void eliminar(Long id);
}
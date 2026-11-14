package com.example.demo.services;

import com.example.demo.dtos.CurriculumRequestDto;
import com.example.demo.dtos.CurriculumResponseDto;
import java.util.List;

public interface CurriculumService {

    CurriculumResponseDto crear(CurriculumRequestDto dto);

    List<CurriculumResponseDto> obtenerTodos();

    CurriculumResponseDto obtenerPorId(Long id);

    CurriculumResponseDto actualizar(Long id, CurriculumRequestDto dto);

    void eliminar(Long id);
}
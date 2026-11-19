package com.example.demo.services;

import com.example.demo.dtos.ProfesorRequestDto;
import com.example.demo.dtos.ProfesorResponseDto;

import java.util.List;

public interface ProfesorService {

    ProfesorResponseDto crear(ProfesorRequestDto dto);

    List<ProfesorResponseDto> obtenerTodos();

    ProfesorResponseDto obtenerPorId(Long id);

    ProfesorResponseDto actualizar(Long id, ProfesorRequestDto dto);

    void eliminar(Long id);
}
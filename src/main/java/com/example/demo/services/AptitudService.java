package com.example.demo.services;

import com.example.demo.dtos.AptitudRequestDto;
import com.example.demo.dtos.AptitudResponseDto;
import java.util.List;

public interface AptitudService {

    AptitudResponseDto crear(AptitudRequestDto dto);

    List<AptitudResponseDto> obtenerTodos();

    AptitudResponseDto obtenerPorId(Long id);

    AptitudResponseDto actualizar(Long id, AptitudRequestDto dto);

    void eliminar(Long id);
}
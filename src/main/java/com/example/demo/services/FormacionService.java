package com.example.demo.services;

import com.example.demo.dtos.FormacionRequestDto;
import com.example.demo.dtos.FormacionResponseDto;
import java.util.List;

public interface FormacionService {

    FormacionResponseDto crear(FormacionRequestDto dto);

    List<FormacionResponseDto> obtenerTodos();

    FormacionResponseDto obtenerPorId(Long id);

    FormacionResponseDto actualizar(Long id, FormacionRequestDto dto);

    void eliminar(Long id);
}
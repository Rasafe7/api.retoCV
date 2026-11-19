package com.example.demo.services.impl;

import com.example.demo.dtos.ProfesorRequestDto;
import com.example.demo.dtos.ProfesorResponseDto;
import com.example.demo.entities.Profesor;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ProfesorRepository;
import com.example.demo.services.ProfesorService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional
    public ProfesorResponseDto crear(ProfesorRequestDto dto) {

        Profesor profesor = new Profesor();

        profesor.setNombre(dto.nombre());
        profesor.setApellidos(dto.apellidos());
        profesor.setDni(dto.dni());
        profesor.setCorreo(dto.correo());

        profesor = profesorRepository.save(profesor);

        return mapToResponseDto(profesor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorResponseDto> obtenerTodos() {

        return profesorRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesorResponseDto obtenerPorId(Long id) {

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor no encontrado con ID: " + id));

        return mapToResponseDto(profesor);
    }

    @Override
    @Transactional
    public ProfesorResponseDto actualizar(Long id, ProfesorRequestDto dto) {

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor no encontrado con ID: " + id));

        profesor.setNombre(dto.nombre());
        profesor.setApellidos(dto.apellidos());
        profesor.setDni(dto.dni());
        profesor.setCorreo(dto.correo());

        profesor = profesorRepository.save(profesor);

        return mapToResponseDto(profesor);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesor no encontrado con ID: " + id));

        profesorRepository.delete(profesor);
    }

    private ProfesorResponseDto mapToResponseDto(Profesor profesor) {

        return new ProfesorResponseDto(
                profesor.getIdProfesor(),
                profesor.getNombre(),
                profesor.getApellidos(),
                profesor.getDni(),
                profesor.getCorreo()
        );
    }
}
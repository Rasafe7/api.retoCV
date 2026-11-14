package com.example.demo.services.impl;

import com.example.demo.dtos.AlumnoRequestDto;
import com.example.demo.dtos.AlumnoResponseDto;
import com.example.demo.entities.Alumno;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.services.AlumnoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional
    public AlumnoResponseDto crear(AlumnoRequestDto dto) {

        Alumno alumno = new Alumno();

        alumno.setNombre(dto.nombre());
        alumno.setApellidos(dto.apellidos());
        alumno.setDni(dto.dni());
        alumno.setCorreo(dto.correo());

        alumno = alumnoRepository.save(alumno);

        return mapToResponseDto(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponseDto> obtenerTodos() {

        return alumnoRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponseDto obtenerPorId(Long id) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + id));

        return mapToResponseDto(alumno);
    }

    @Override
    @Transactional
    public AlumnoResponseDto actualizar(Long id, AlumnoRequestDto dto) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + id));

        alumno.setNombre(dto.nombre());
        alumno.setApellidos(dto.apellidos());
        alumno.setDni(dto.dni());
        alumno.setCorreo(dto.correo());

        alumno = alumnoRepository.save(alumno);

        return mapToResponseDto(alumno);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + id));

        alumnoRepository.delete(alumno);
    }

    private AlumnoResponseDto mapToResponseDto(Alumno alumno) {

        return new AlumnoResponseDto(
                alumno.getIdAlumno(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getDni(),
                alumno.getCorreo()
        );
    }
}
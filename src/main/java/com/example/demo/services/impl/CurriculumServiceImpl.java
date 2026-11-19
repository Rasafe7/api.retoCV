package com.example.demo.services.impl;

import com.example.demo.dtos.CurriculumRequestDto;
import com.example.demo.dtos.CurriculumResponseDto;
import com.example.demo.entities.Curriculum;
import com.example.demo.entities.Alumno;
import com.example.demo.entities.Profesor;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CurriculumRepository;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.repositories.ProfesorRepository;
import com.example.demo.services.CurriculumService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final AlumnoRepository alumnoRepository;
    private final ProfesorRepository profesorRepository;

    public CurriculumServiceImpl(
            CurriculumRepository curriculumRepository,
            AlumnoRepository alumnoRepository,
            ProfesorRepository profesorRepository) {

        this.curriculumRepository = curriculumRepository;
        this.alumnoRepository = alumnoRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    @Transactional
    public CurriculumResponseDto crear(CurriculumRequestDto dto) {

        if ((dto.idAlumno() == null && dto.idProfesor() == null)
                || (dto.idAlumno() != null && dto.idProfesor() != null)) {
            throw new IllegalArgumentException(
                    "El currículum debe pertenecer a un alumno o a un profesor, pero no a ambos.");
        }

        Curriculum curriculum = new Curriculum();

        if (dto.idAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(dto.idAlumno())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Alumno no encontrado con ID: " + dto.idAlumno()));

            curriculum.setAlumno(alumno);
        }

        if (dto.idProfesor() != null) {
            Profesor profesor = profesorRepository.findById(dto.idProfesor())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Profesor no encontrado con ID: " + dto.idProfesor()));

            curriculum.setProfesor(profesor);
        }

        curriculum = curriculumRepository.save(curriculum);

        return mapToResponseDto(curriculum);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurriculumResponseDto> obtenerTodos() {

        return curriculumRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CurriculumResponseDto obtenerPorId(Long id) {

        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currículum no encontrado con ID: " + id));

        return mapToResponseDto(curriculum);
    }

    @Override
    @Transactional
    public CurriculumResponseDto actualizar(Long id, CurriculumRequestDto dto) {

        if ((dto.idAlumno() == null && dto.idProfesor() == null)
                || (dto.idAlumno() != null && dto.idProfesor() != null)) {
            throw new IllegalArgumentException(
                    "El currículum debe pertenecer a un alumno o a un profesor, pero no a ambos.");
        }

        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currículum no encontrado con ID: " + id));

        curriculum.setAlumno(null);
        curriculum.setProfesor(null);

        if (dto.idAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(dto.idAlumno())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Alumno no encontrado con ID: " + dto.idAlumno()));

            curriculum.setAlumno(alumno);
        }

        if (dto.idProfesor() != null) {
            Profesor profesor = profesorRepository.findById(dto.idProfesor())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Profesor no encontrado con ID: " + dto.idProfesor()));

            curriculum.setProfesor(profesor);
        }

        curriculum = curriculumRepository.save(curriculum);

        return mapToResponseDto(curriculum);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currículum no encontrado con ID: " + id));

        curriculumRepository.delete(curriculum);
    }

    private CurriculumResponseDto mapToResponseDto(Curriculum curriculum) {

        return new CurriculumResponseDto(
                curriculum.getId(),
                curriculum.getAlumno() != null
                        ? curriculum.getAlumno().getIdAlumno() : null,
                curriculum.getProfesor() != null
                        ? curriculum.getProfesor().getIdProfesor() : null
        );
    }
}
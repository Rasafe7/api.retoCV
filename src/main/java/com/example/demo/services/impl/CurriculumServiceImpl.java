package com.example.demo.services.impl;

import com.example.demo.dtos.CurriculumRequestDto;
import com.example.demo.dtos.CurriculumResponseDto;
import com.example.demo.entities.Curriculum;
import com.example.demo.entities.Alumno;
import com.example.demo.entities.Aptitud;
import com.example.demo.entities.Experiencia;
import com.example.demo.entities.Formacion;

import com.example.demo.exceptions.ResourceNotFoundException;

import com.example.demo.repositories.CurriculumRepository;
import com.example.demo.repositories.AlumnoRepository;
import com.example.demo.repositories.AptitudRepository;
import com.example.demo.repositories.ExperienciaRepository;
import com.example.demo.repositories.FormacionRepository;

import com.example.demo.services.CurriculumService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final AlumnoRepository alumnoRepository;
    private final AptitudRepository aptitudRepository;
    private final ExperienciaRepository experienciaRepository;
    private final FormacionRepository formacionRepository;

    public CurriculumServiceImpl(
            CurriculumRepository curriculumRepository,
            AlumnoRepository alumnoRepository,
            AptitudRepository aptitudRepository,
            ExperienciaRepository experienciaRepository,
            FormacionRepository formacionRepository) {

        this.curriculumRepository = curriculumRepository;
        this.alumnoRepository = alumnoRepository;
        this.aptitudRepository = aptitudRepository;
        this.experienciaRepository = experienciaRepository;
        this.formacionRepository = formacionRepository;
    }

    @Override
    @Transactional
    public CurriculumResponseDto crear(CurriculumRequestDto dto) {

        Curriculum curriculum = new Curriculum();

        Alumno alumno = alumnoRepository.findById(dto.idAlumno())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + dto.idAlumno()));

        curriculum.setAlumno(alumno);

        if (dto.idAptitud() != null) {
            Aptitud aptitud = aptitudRepository.findById(dto.idAptitud())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Aptitud no encontrada con ID: " + dto.idAptitud()));

            curriculum.setAptitud(aptitud);
        }

        if (dto.idExperiencia() != null) {
            Experiencia experiencia = experienciaRepository.findById(dto.idExperiencia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Experiencia no encontrada con ID: " + dto.idExperiencia()));

            curriculum.setExperiencia(experiencia);
        }

        if (dto.idFormacion() != null) {
            Formacion formacion = formacionRepository.findById(dto.idFormacion())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Formación no encontrada con ID: " + dto.idFormacion()));

            curriculum.setFormacion(formacion);
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

        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Currículum no encontrado con ID: " + id));

        Alumno alumno = alumnoRepository.findById(dto.idAlumno())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Alumno no encontrado con ID: " + dto.idAlumno()));

        curriculum.setAlumno(alumno);

        Aptitud aptitud = null;
        if (dto.idAptitud() != null) {
            aptitud = aptitudRepository.findById(dto.idAptitud())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Aptitud no encontrada con ID: " + dto.idAptitud()));
        }
        curriculum.setAptitud(aptitud);

        Experiencia experiencia = null;
        if (dto.idExperiencia() != null) {
            experiencia = experienciaRepository.findById(dto.idExperiencia())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Experiencia no encontrada con ID: " + dto.idExperiencia()));
        }
        curriculum.setExperiencia(experiencia);

        Formacion formacion = null;
        if (dto.idFormacion() != null) {
            formacion = formacionRepository.findById(dto.idFormacion())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Formación no encontrada con ID: " + dto.idFormacion()));
        }
        curriculum.setFormacion(formacion);

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
                curriculum.getAlumno().getIdAlumno(),
                curriculum.getAptitud() != null
                        ? curriculum.getAptitud().getId() : null,
                curriculum.getExperiencia() != null
                        ? curriculum.getExperiencia().getIdExperiencia() : null,
                curriculum.getFormacion() != null
                        ? curriculum.getFormacion().getIdFormacion() : null
        );
    }
}
package com.example.demo.services.impl;

import com.example.demo.dtos.ExperienciaRequestDto;
import com.example.demo.dtos.ExperienciaResponseDto;
import com.example.demo.entities.Experiencia;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ExperienciaRepository;
import com.example.demo.repositories.CurriculumRepository;
import com.example.demo.services.ExperienciaService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;
    private final CurriculumRepository curriculumRepository;

    public ExperienciaServiceImpl(
            ExperienciaRepository experienciaRepository,
            CurriculumRepository curriculumRepository) {
        this.experienciaRepository = experienciaRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    @Transactional
    public ExperienciaResponseDto crear(ExperienciaRequestDto dto) {

        Experiencia experiencia = new Experiencia();

        experiencia.setNombre(dto.nombre());
        experiencia.setDescripcion(dto.descripcion());

        experiencia = experienciaRepository.save(experiencia);

        return mapToResponseDto(experiencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDto> obtenerTodos() {

        return experienciaRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExperienciaResponseDto obtenerPorId(Long id) {

        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experiencia no encontrada con ID: " + id));

        return mapToResponseDto(experiencia);
    }

    @Override
    @Transactional
    public ExperienciaResponseDto actualizar(Long id, ExperienciaRequestDto dto) {

        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experiencia no encontrada con ID: " + id));

        experiencia.setNombre(dto.nombre());
        experiencia.setDescripcion(dto.descripcion());

        experiencia = experienciaRepository.save(experiencia);

        return mapToResponseDto(experiencia);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Experiencia no encontrada con ID: " + id));

        if (experiencia.getCurriculum() != null) {
            var curriculum = experiencia.getCurriculum();
            curriculum.setExperiencia(null);
            curriculumRepository.save(curriculum);
        }

        experienciaRepository.delete(experiencia);
    }

    private ExperienciaResponseDto mapToResponseDto(Experiencia experiencia) {

        return new ExperienciaResponseDto(
                experiencia.getIdExperiencia(),
                experiencia.getNombre(),
                experiencia.getDescripcion()
        );
    }
}
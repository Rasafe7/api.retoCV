package com.example.demo.services.impl;

import com.example.demo.dtos.AptitudRequestDto;
import com.example.demo.dtos.AptitudResponseDto;
import com.example.demo.entities.Aptitud;
import com.example.demo.entities.Curriculum;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.AptitudRepository;
import com.example.demo.repositories.CurriculumRepository;
import com.example.demo.services.AptitudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AptitudServiceImpl implements AptitudService {

    private final AptitudRepository aptitudRepository;
    private final CurriculumRepository curriculumRepository;

    public AptitudServiceImpl(
            AptitudRepository aptitudRepository,
            CurriculumRepository curriculumRepository) {
        this.aptitudRepository = aptitudRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    @Transactional
    public AptitudResponseDto crear(AptitudRequestDto dto) {

        Aptitud aptitud = new Aptitud();
        aptitud.setNombre(dto.nombre());

        if (dto.idCurriculum() != null) {
            Curriculum curriculum = curriculumRepository.findById(dto.idCurriculum())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Currículum no encontrado con ID: " + dto.idCurriculum()));

            aptitud.setCurriculum(curriculum);
        }

        aptitud = aptitudRepository.save(aptitud);

        return mapToResponseDto(aptitud);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AptitudResponseDto> obtenerTodos() {

        return aptitudRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AptitudResponseDto obtenerPorId(Long id) {

        Aptitud aptitud = aptitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aptitud no encontrada con ID: " + id));

        return mapToResponseDto(aptitud);
    }

    @Override
    @Transactional
    public AptitudResponseDto actualizar(Long id, AptitudRequestDto dto) {

        Aptitud aptitud = aptitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aptitud no encontrada con ID: " + id));

        aptitud.setNombre(dto.nombre());

        if (dto.idCurriculum() != null) {
            Curriculum curriculum = curriculumRepository.findById(dto.idCurriculum())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Currículum no encontrado con ID: " + dto.idCurriculum()));

            aptitud.setCurriculum(curriculum);
        } else {
            aptitud.setCurriculum(null);
        }

        aptitud = aptitudRepository.save(aptitud);

        return mapToResponseDto(aptitud);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Aptitud aptitud = aptitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aptitud no encontrada con ID: " + id));

        aptitudRepository.delete(aptitud);
    }

    private AptitudResponseDto mapToResponseDto(Aptitud aptitud) {

        return new AptitudResponseDto(
                aptitud.getId(),
                aptitud.getNombre()
        );
    }
}
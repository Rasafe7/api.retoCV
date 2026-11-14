package com.example.demo.services.impl;

import com.example.demo.dtos.FormacionRequestDto;
import com.example.demo.dtos.FormacionResponseDto;
import com.example.demo.entities.Formacion;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.FormacionRepository;
import com.example.demo.repositories.CurriculumRepository;
import com.example.demo.services.FormacionService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormacionServiceImpl implements FormacionService {

    private final FormacionRepository formacionRepository;
    private final CurriculumRepository curriculumRepository;

    public FormacionServiceImpl(
            FormacionRepository formacionRepository,
            CurriculumRepository curriculumRepository) {
        this.formacionRepository = formacionRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @Override
    @Transactional
    public FormacionResponseDto crear(FormacionRequestDto dto) {

        Formacion formacion = new Formacion();

        formacion.setNombre(dto.nombre());
        formacion.setDescripcion(dto.descripcion());

        formacion = formacionRepository.save(formacion);

        return mapToResponseDto(formacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormacionResponseDto> obtenerTodos() {

        return formacionRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FormacionResponseDto obtenerPorId(Long id) {

        Formacion formacion = formacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Formación no encontrada con ID: " + id));

        return mapToResponseDto(formacion);
    }

    @Override
    @Transactional
    public FormacionResponseDto actualizar(Long id, FormacionRequestDto dto) {

        Formacion formacion = formacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Formación no encontrada con ID: " + id));

        formacion.setNombre(dto.nombre());
        formacion.setDescripcion(dto.descripcion());

        formacion = formacionRepository.save(formacion);

        return mapToResponseDto(formacion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Formacion formacion = formacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Formación no encontrada con ID: " + id));

        if (formacion.getCurriculum() != null) {
            var curriculum = formacion.getCurriculum();
            curriculum.setFormacion(null);
            curriculumRepository.save(curriculum);
        }

        formacionRepository.delete(formacion);
    }

    private FormacionResponseDto mapToResponseDto(Formacion formacion) {

        return new FormacionResponseDto(
                formacion.getIdFormacion(),
                formacion.getNombre(),
                formacion.getDescripcion()
        );
    }
}
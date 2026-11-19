package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}
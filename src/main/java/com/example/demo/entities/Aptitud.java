package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "aptitudes", schema = "basecurriculum")
public class Aptitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aptitud", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curriculum")
    private Curriculum curriculum;

    public Aptitud() {
    }

    public Aptitud(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}

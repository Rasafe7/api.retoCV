package com.example.demo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "aptitudes", schema = "basecurriculum")
public class Aptitud {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_aptitud", nullable =  false, unique = true)
    private Long id;

    @Column (name = "nombre", nullable = false, length = 100)
    private String nombre;

    @OneToOne(mappedBy = "aptitud", cascade = CascadeType.REMOVE)
    private Curriculum curriculum;
    
    public Aptitud(){
    }

    public  Aptitud(String nombre){
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

    



}

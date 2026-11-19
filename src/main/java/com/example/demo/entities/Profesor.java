package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "profesores", schema = "basecurriculum")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor", unique = true)
    private Long idProfesor;

    @Column(name = "nombre_profesor", nullable = false)
    private String nombre;

    @Column(name = "apellidos_profesor", nullable = false)
    private String apellidos;

    @Column(name = "dni_profesor", nullable = false, unique = true)
    private String dni;

    @Column(name = "correo_profesor", nullable = false)
    private String correo;

    @OneToOne(mappedBy = "profesor", cascade = CascadeType.REMOVE)
    private Curriculum curriculum;

    public Profesor() {
    }

    public Profesor(String nombre, String apellidos, String dni, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.correo = correo;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
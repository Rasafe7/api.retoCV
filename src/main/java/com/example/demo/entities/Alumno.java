package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos", schema = "basecurriculum")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno", unique = true)
    private Long idAlumno;

    @Column(name = "nombre_alumno", nullable = false)
    private String nombre;

    @Column(name = "apellidos_alumno", nullable = false)
    private String apellidos;

    @Column(name = "dni_alumno", nullable = false, unique = true)
    private String dni;

    @Column(name = "correo_alumno", nullable = false)
    private String correo;

    @OneToOne(mappedBy = "alumno", cascade = CascadeType.REMOVE)
    private Curriculum curriculum;
     

    public Alumno() {

    }

    public Alumno(String nombre, String apellidos,
            String dni, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.correo = correo;

    }


    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
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



}

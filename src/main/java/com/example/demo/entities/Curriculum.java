
package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "curriculums", schema = "basecurriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curriculum", nullable = false)
    private Long id;

    // Relación con Alumno
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno", nullable = false, foreignKey = @ForeignKey(name = "fk_id_alumno"))
    private Alumno alumno;

    // Relación con Aptitud
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aptitud", referencedColumnName = "id_aptitud", nullable = true, foreignKey = @ForeignKey(name = "fk_id_aptitud"))
    private Aptitud aptitud;

    // Relación con Experiencia
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experiencia", referencedColumnName = "id_experiencia", nullable = true, foreignKey = @ForeignKey(name = "fk_id_experiencia"))
    private Experiencia experiencia;

    // Relación con Formacion
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formacion", referencedColumnName = "id_formacion", nullable = true, foreignKey = @ForeignKey(name = "fk_id_formacion"))
    private Formacion formacion;

    public Curriculum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Aptitud getAptitud() {
        return aptitud;
    }

    public void setAptitud(Aptitud aptitud) {
        this.aptitud = aptitud;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public Formacion getFormacion() {
        return formacion;
    }

    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
    }

    // Aquí añadirás los getters y setters

    

}

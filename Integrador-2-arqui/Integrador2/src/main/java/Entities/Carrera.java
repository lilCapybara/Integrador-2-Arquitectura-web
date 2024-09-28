package Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrera;
    @Column
    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera")
    private List<Inscripcion> inscripciones;

    public Carrera(){}

    public Carrera(String nombreCarrera, List<Inscripcion> inscripciones) {
        this.nombreCarrera = nombreCarrera;
        this.inscripciones = new ArrayList<>(inscripciones);
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
}

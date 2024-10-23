package DTOs;

import jakarta.persistence.Column;

public class CarreraDTO {
    private int idCarrera;
    private String nombreCarrera;
    private long cantInscriptos;
    private long cantEgresados;
    private int anioInscripcion;

    public CarreraDTO(int idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = 0;
        this.cantEgresados = 0;
        this.anioInscripcion = 0;
    }

    public CarreraDTO(int idCarrera, String nombreCarrera, long cantInscriptos) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
        this.cantEgresados = 0;
        this.anioInscripcion = 0;
    }

    public CarreraDTO(int idCarrera, String nombreCarrera, long cantInscriptos, int anioInscripcion) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
        this.anioInscripcion = anioInscripcion;
    }

    public CarreraDTO(int idCarrera, String nombreCarrera, long cantInscriptos, long cantEgresados, int anioInscripcion) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
        this.cantEgresados = cantEgresados;
        this.anioInscripcion = anioInscripcion;
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

    public long getCantInscriptos() {
        return cantInscriptos;
    }

    public void setCantInscriptos(int cantInscriptos) {
        this.cantInscriptos = cantInscriptos;
    }

    public long getCantEgresados() {
        return cantEgresados;
    }

    public void setCantEgresados(int cantEgresados) {
        this.cantEgresados = cantEgresados;
    }

    @Override
    public String toString() {
        if (this.anioInscripcion == 0) {
            return "CarreraDTO{" +
                    "nombreCarrera='" + this.nombreCarrera + '\'' +
                    ", cantInscriptos=" + this.cantInscriptos +
                    ", cantEgresados=" + this.cantEgresados +
                    '}';
        }
        return "CarreraDTO{" +
                "nombreCarrera='" + this.nombreCarrera + '\'' +
                ", cantInscriptos=" + this.cantInscriptos +
                ", cantEgresados=" + this.cantEgresados +
                ", anioInscripcion=" + this.anioInscripcion +
                '}';
    }
}

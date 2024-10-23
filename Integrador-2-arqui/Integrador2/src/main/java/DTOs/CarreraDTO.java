package DTOs;

import jakarta.persistence.Column;

public class CarreraDTO {
    private int idCarrera;
    private String nombreCarrera;
    private int cantInscriptos;
    private int cantEgresados;

    public CarreraDTO(int idCarrera, String nombreCarrera, int cantInscriptos, int cantEgresados) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
        this.cantEgresados = cantEgresados;
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

    public int getCantInscriptos() {
        return cantInscriptos;
    }

    public void setCantInscriptos(int cantInscriptos) {
        this.cantInscriptos = cantInscriptos;
    }

    public int getCantEgresados() {
        return cantEgresados;
    }

    public void setCantEgresados(int cantEgresados) {
        this.cantEgresados = cantEgresados;
    }

    @Override
    public String toString(){
        return "CarreraDTO{" +
                "nombreCarrera='" + this.nombreCarrera + '\'' +
                ", cantInscriptos=" + this.cantInscriptos +
                ", cantEgresados=" + this.cantEgresados +
                '}';
    }
}

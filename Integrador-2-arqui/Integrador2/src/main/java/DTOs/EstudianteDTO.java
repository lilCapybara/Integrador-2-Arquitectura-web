package DTOs;

import jakarta.persistence.Column;

public class EstudianteDTO {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String genero;
    private int documento;
    private String ciudadResidencia;
    private int libretaUniversitaria;

    public EstudianteDTO(int idEstudiante, String nombre, String apellido, Integer edad, String genero, int documento, String ciudadResidencia, int libretaUniversitaria) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.documento = documento;
        this.ciudadResidencia = ciudadResidencia;
        this.libretaUniversitaria = libretaUniversitaria;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public int getLibretaUniversitaria() {
        return libretaUniversitaria;
    }

    public void setLibretaUniversitaria(int libretaUniversitaria) {
        this.libretaUniversitaria = libretaUniversitaria;
    }

    @Override
    public String toString(){
        return "EstudianteDTO{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", documento='" + documento + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", libretaUniversitaria='" + libretaUniversitaria + '\'' +
                '}';
    }
}

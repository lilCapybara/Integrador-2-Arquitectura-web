package org.example;

import Entities.*;
import HelperSQL.HelperSQL;
import Services.*;
import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceIntegrador2");

        EstudianteService estudianteService = new EstudianteService();
        CarreraService carreraService = new CarreraService(emf);
        InscripcionService inscripcionService = new InscripcionService();

        //Creo las carreras
        Carrera carrera = new Carrera();
        carrera.setNombreCarrera("Ingeniería de Software");
        carreraService.createCarrera(carrera);

        Carrera carrera2 = new Carrera();
        carrera2.setNombreCarrera("Ingeniería Civil");
        carreraService.createCarrera(carrera2);

        Carrera carrera3 = new Carrera();
        carrera3.setNombreCarrera("Medicina");
        carreraService.createCarrera(carrera3);

        Carrera carrera4 = new Carrera();
        carrera4.setNombreCarrera("Arquitectura");
        carreraService.createCarrera(carrera4);

        Carrera carrera5 = new Carrera();
        carrera5.setNombreCarrera("Derecho");
        carreraService.createCarrera(carrera5);

        // a) Doy de alta a los estudiantes

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setEdad(22);
        estudiante.setGenero("Masculino");
        estudiante.setDocumento(12345678);
        estudiante.setCiudadResidencia("Buenos Aires");
        estudiante.setLibretaUniversitaria(20230001);
        estudianteService.insertEstudiante(estudiante);

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombre("Ana");
        estudiante2.setApellido("García");
        estudiante2.setEdad(23);
        estudiante2.setGenero("Femenino");
        estudiante2.setDocumento(87654321);
        estudiante2.setCiudadResidencia("Córdoba");
        estudiante2.setLibretaUniversitaria(20230002);
        estudianteService.insertEstudiante(estudiante2);

        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombre("Pedro");
        estudiante3.setApellido("López");
        estudiante3.setEdad(21);
        estudiante3.setGenero("Masculino");
        estudiante3.setDocumento(34567890);
        estudiante3.setCiudadResidencia("Rosario");
        estudiante3.setLibretaUniversitaria(20230003);
        estudianteService.insertEstudiante(estudiante3);

        Estudiante estudiante4 = new Estudiante();
        estudiante4.setNombre("Lucía");
        estudiante4.setApellido("Fernández");
        estudiante4.setEdad(24);
        estudiante4.setGenero("Femenino");
        estudiante4.setDocumento(45678901);
        estudiante4.setCiudadResidencia("Mendoza");
        estudiante4.setLibretaUniversitaria(20230004);
        estudianteService.insertEstudiante(estudiante4);

        Estudiante estudiante5 = new Estudiante();
        estudiante5.setNombre("Juan");
        estudiante5.setApellido("Martínez");
        estudiante5.setEdad(25);
        estudiante5.setGenero("Masculino");
        estudiante5.setDocumento(56789012);
        estudiante5.setCiudadResidencia("La Plata");
        estudiante5.setLibretaUniversitaria(20230005);
        estudianteService.insertEstudiante(estudiante5);

        Estudiante estudiante6 = new Estudiante();
        estudiante6.setNombre("Marta");
        estudiante6.setApellido("Sanchez");
        estudiante6.setEdad(27);
        estudiante6.setGenero("Femenino");
        estudiante6.setDocumento(56129412);
        estudiante6.setCiudadResidencia("Tandil");
        estudiante6.setLibretaUniversitaria(20230006);
        estudianteService.insertEstudiante(estudiante6);

        Estudiante estudiante7 = new Estudiante();
        estudiante7.setNombre("Marcos");
        estudiante7.setApellido("Paz");
        estudiante7.setEdad(22);
        estudiante7.setGenero("Masculino");
        estudiante7.setDocumento(65742012);
        estudiante7.setCiudadResidencia("Carlos Paz");
        estudiante7.setLibretaUniversitaria(20230007);
        estudianteService.insertEstudiante(estudiante7);

        // b) Matriculo los estudiantes en sus carreras

        inscripcionService.matricularEstudiante(estudiante, carrera, 1);

        inscripcionService.matricularEstudiante(estudiante2, carrera3, 2);

        inscripcionService.matricularEstudiante(estudiante3, carrera4, 1);

        inscripcionService.matricularEstudiante(estudiante4, carrera5, 3);

        inscripcionService.matricularEstudiante(estudiante5, carrera2, 1);

        inscripcionService.matricularEstudiante(estudiante6, carrera2, 4);

        inscripcionService.matricularEstudiante(estudiante7, carrera, 2);

        // c) Recupero a todos los estudiantes, ordenados por su edad

        List<Estudiante> estudiantesPorEdad = estudianteService.getEstudiantesByEdad();
        estudiantesPorEdad.forEach(e -> System.out.println("Lista de estudiantes ordenados segun su edad:" + e.getNombre() + " " + e.getApellido() + " (" + e.getEdad() + ")"));

        // d) Recupero estudiante por libreta universitaria
        Estudiante estudianteRecuperado = estudianteService.getEstudianteByLibreta(20230001);
        System.out.println("El estudiante con el numero de libreta universitaria ingresada es: " + estudianteRecuperado.getNombre() + " " + estudianteRecuperado.getApellido());

        // e) Recupero todos los estudiantes por género
        List<Estudiante> estudiantesPorGenero = estudianteService.getEstudianteByGenero("Masculino");
        System.out.println("Estudiantes del genero solicitado:");
        estudiantesPorGenero.forEach(e -> System.out.println(e.getNombre() + " " + e.getApellido()));

        // f) Recupero carreras ordenadas por cantidad de inscriptos
        List<Object[]> carrerasConInscriptos = carreraService.getCarrerasByInscriptos();
        for (Object[] result : carrerasConInscriptos) {
            System.out.println("Carrera: " + result[0] + ", Cantidad de inscriptos: " + result[1]);
        }

        // g) Recupero los estudiantes segun carrera y ciudad de residencia
        List<Estudiante> estudiantesPorCarreraYCiudad = estudianteService.getEstudianteByCarreraAndCiudad("Medicina","Córdoba");
        System.out.println("Estudiantes con la carrera y ciudad de residencia solicitadas:");
        estudiantesPorCarreraYCiudad.forEach(e -> System.out.println(e.getNombre() + " " + e.getApellido()));

        //Testeo de altas, bajas y updates
/*
        Estudiante estudianteTest = new Estudiante();
        estudianteTest.setNombre("Carlos");
        estudianteTest.setApellido("Rodriguez");
        estudianteTest.setEdad(49);
        estudianteTest.setGenero("Masculino");
        estudianteTest.setDocumento(39806751);
        estudianteTest.setCiudadResidencia("Ushuaia");
        estudianteTest.setLibretaUniversitaria(20230016);

        estudianteService.updateEstudiante(estudiante,estudianteTest);
        estudianteService.deleteEstudiante(estudianteTest);

        Carrera carreraTest = new Carrera();
        carreraTest.setNombreCarrera("Carrera de prueba");


        carreraService.updateCarrera(carrera,carreraTest);
        carreraService.deleteCarrera(carrera);
*/

        //Elimino las tablas al finalizar para facilitar pruebas futuras
        HelperSQL helper= new HelperSQL();
        //helper.dropAllTables(emf);


        emf.close();
    }
}
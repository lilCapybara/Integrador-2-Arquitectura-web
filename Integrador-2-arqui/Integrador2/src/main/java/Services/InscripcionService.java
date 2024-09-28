package Services;

import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class InscripcionService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceIntegrador2");

    public void crearInscripcion(Estudiante estudiante, Carrera carrera, int antiguedad, boolean estadoGraduacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCarrera(carrera);
            inscripcion.setAntiguedad(antiguedad);
            inscripcion.setGraduado(estadoGraduacion);

            em.persist(inscripcion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Inscripcion obtenerInscripcionPorId(int idInscripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Inscripcion.class, idInscripcion);
        } finally {
            em.close();
        }
    }

    public void matricularEstudiante(Estudiante estudiante, Carrera carrera, int antiguedad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCarrera(carrera);
        inscripcion.setAntiguedad(antiguedad);
        inscripcion.setGraduado(false);  // No graduado inicialmente

        em.persist(inscripcion);
        em.getTransaction().commit();
        em.close();
    }



}


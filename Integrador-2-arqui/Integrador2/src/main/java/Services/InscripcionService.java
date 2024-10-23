package Services;

import Entities.*;
import jakarta.persistence.*;

import java.util.List;

public class InscripcionService {

    private EntityManagerFactory emf;

    private static InscripcionService instance;

    private InscripcionService() {
        this.emf = Persistence.createEntityManagerFactory("persistenceIntegrador2");;
    }

    public static InscripcionService getInstance() {
        if (instance == null) {
            instance = new InscripcionService();
        }
        return instance;
    }

    public void crearInscripcion(Estudiante estudiante, Carrera carrera, int antiguedad, int anioInscripcion, boolean estadoGraduacion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setEstudiante(estudiante);
            inscripcion.setCarrera(carrera);
            inscripcion.setAntiguedad(antiguedad);
            inscripcion.setAnioInscripcion(anioInscripcion);
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
/*
    public Inscripcion obtenerInscripcionPorId(int idInscripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Inscripcion.class, idInscripcion);
        } finally {
            em.close();
        }
    }
*/
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera, int antiguedad, int anioInscripcion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCarrera(carrera);
        inscripcion.setAntiguedad(antiguedad);
        inscripcion.setAnioInscripcion(anioInscripcion);
        inscripcion.setGraduado(false);  // No graduado inicialmente

        em.persist(inscripcion);
        em.getTransaction().commit();
        em.close();
    }

    private Inscripcion obtenerInscripcion(Estudiante estudiante, Carrera carrera){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i WHERE i.estudiante.idEstudiante = :idEstudiante AND i.carrera.idCarrera = :idCarrera",
                    Inscripcion.class);
            query.setParameter("idEstudiante", estudiante.getIdEstudiante());
            query.setParameter("idCarrera", carrera.getIdCarrera());
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        finally {
            em.close();
        }
    }

    public void egresarEstudiante(Estudiante estudiante, Carrera carrera){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Inscripcion insc = this.obtenerInscripcion(estudiante,carrera);
        if (insc!=null){
            insc.setGraduado(true);
            em.merge(insc);
            em.getTransaction().commit();
        }
        em.close();
    }


}


package Services;

import Entities.*;
import jakarta.persistence.*;

import java.util.List;

public class EstudianteService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceIntegrador2");

    public void insertEstudiante(Estudiante estudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();
    }

    public void updateEstudiante(Estudiante estudianteExistente, Estudiante estudianteNuevo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Estudiante estudiante = em.find(Estudiante.class, estudianteExistente.getIdEstudiante());

            if (estudiante != null) {

                estudiante.setNombre(estudianteNuevo.getNombre());
                estudiante.setApellido(estudianteNuevo.getApellido());
                estudiante.setEdad(estudianteNuevo.getEdad());
                estudiante.setGenero(estudianteNuevo.getGenero());
                estudiante.setDocumento(estudianteNuevo.getDocumento());
                estudiante.setCiudadResidencia(estudianteNuevo.getCiudadResidencia());
                estudiante.setLibretaUniversitaria(estudianteNuevo.getLibretaUniversitaria());

                em.merge(estudiante);
                tx.commit();
                System.out.println("Estudiante modificado correctamente");
            } else {
                System.out.println("El estudiante no fue encontrado.");
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteEstudiante(Estudiante estudiante) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            if (estudiante != null) {
                tx.begin();
                em.remove(estudiante);
                tx.commit();
                System.out.println("Estudiante eliminado con exito");
            } else {
                System.out.println("El estudiante no existe.");
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public Estudiante getEstudianteByLibreta(int numeroLibreta) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = :numeroLibreta", Estudiante.class)
                    .setParameter("numeroLibreta", numeroLibreta)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Estudiante> getEstudianteByGenero(String genero) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery(
                    "SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class);
            query.setParameter("genero", genero);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Estudiante> getEstudiantesByEdad() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.edad ASC", Estudiante.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Estudiante> getEstudianteByCarreraAndCiudad(String carreraNombre, String ciudad) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Estudiante> query = em.createQuery(
                "SELECT i.estudiante FROM Inscripcion i WHERE i.carrera.nombreCarrera = :carrera AND i.estudiante.ciudadResidencia = :ciudad",
                Estudiante.class);
        query.setParameter("carrera", carreraNombre);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }

}

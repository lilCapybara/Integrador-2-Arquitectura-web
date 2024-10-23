package Services;

import DTOs.CarreraDTO;
import Entities.*;
import jakarta.persistence.*;

import java.util.List;

public class CarreraService {

    private EntityManagerFactory emf;

    public CarreraService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void createCarrera(Carrera carrera) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(carrera);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Carrera> getCarreras() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
        } finally {
            em.close();
        }
    }


    public Carrera getCarreraById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Carrera.class, id);
        } finally {
            em.close();
        }
    }

    public void updateCarrera(Carrera carreraExistente, Carrera carreraNueva) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Carrera carrera = em.find(Carrera.class, carreraExistente.getIdCarrera());

            if (carrera != null) {
                carrera.setNombreCarrera(carreraNueva.getNombreCarrera());

                em.merge(carrera);
                tx.commit();
                System.out.println("Carrera modificada correctamente");
            } else {
                System.out.println("La carrera no fue encontrada.");
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


    public void deleteCarrera(Carrera carrera) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {

            if (carrera != null) {
                tx.begin();
                em.remove(carrera);
                tx.commit();
                System.out.println("Carrera borrada con exito");
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

    public List<CarreraDTO> getCarrerasByInscriptos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<CarreraDTO> query = em.createQuery(
                "SELECT new DTOs.CarreraDTO(c.idCarrera, c.nombreCarrera, COUNT(i), 0) " +
                        "FROM Carrera c JOIN c.inscripciones i " +
                        "GROUP BY c.idCarrera, c.nombreCarrera " +
                        "HAVING COUNT(i) > 0 " + //Filtra por carreras con al menos una inscripcion
                        "ORDER BY COUNT(i) DESC",
                CarreraDTO.class);
        return query.getResultList();

    }

    public List<Object[]> generarReporteCarreras() {
        EntityManager em = emf.createEntityManager();

        List<Object[]> resultado = null;

        try {
            resultado = em.createQuery(
                            "SELECT c.nombreCarrera, i.anioInscripcion, " +
                                    "COUNT(i) AS cantidadInscriptos, " +
                                    "SUM(CASE WHEN i.graduado = true THEN 1 ELSE 0 END) AS cantidadEgresados " +
                                    "FROM Inscripcion i " +
                                    "JOIN i.carrera c " +
                                    "GROUP BY c.nombreCarrera, i.anioInscripcion " +
                                    "ORDER BY c.nombreCarrera ASC, i.anioInscripcion ASC", Object[].class)
                    .getResultList();

        } finally {
            em.close();
        }

        return resultado;
    }



}


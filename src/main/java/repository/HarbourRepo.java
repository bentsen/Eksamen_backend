package repository;

import dtos.BoatDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HarbourRepo {

    private static HarbourRepo instance;
    private static EntityManagerFactory emf;

    private HarbourRepo() {}


    public static HarbourRepo getRepositoryExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<BoatDTO> getAllBoatsInHarbour(int id) {
        EntityManager em = getEntityManager();
        List<BoatDTO> boatDTOs = new ArrayList<>();

        try {
            TypedQuery<Boat> typedQuery = em.createQuery("SELECT boat FROM Boat boat WHERE boat.harbour.id is not null and boat.harbour.id = :id", Boat.class);
            typedQuery.setParameter("id", id);
            List<Boat> boats = typedQuery.getResultList();

            for (Boat boat : boats) {
                boatDTOs.add(new BoatDTO(boat));
            }
        } finally {
            em.close();
        }
        return boatDTOs;
    }
}

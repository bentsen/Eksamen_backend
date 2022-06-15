package repository;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BoatRepo {

    private static BoatRepo instance;
    private static EntityManagerFactory emf;

    private BoatRepo() {}


    public static BoatRepo getRepositoryExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BoatRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public BoatDTO getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, id);
        if (boat == null)
            throw new EntityNotFoundException("The Owner entity with ID: "+id+" was not found");
        return new BoatDTO(boat);
    }

    public List<OwnerDTO> ownsBoatById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        List<OwnerDTO> foundOwners = new ArrayList<>();

        try {
            Boat boat = em.find(Boat.class, id);
            List<Owner> boatOwners = boat.getOwners();

            for (Owner boatOwner : boatOwners) {
                foundOwners.add(new OwnerDTO(boatOwner));
            }
        } finally {
            em.close();
        }

        return foundOwners;
    }

}

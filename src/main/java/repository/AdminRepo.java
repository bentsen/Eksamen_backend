package repository;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AdminRepo {

    private static AdminRepo instance;
    private static EntityManagerFactory emf;

    private AdminRepo() {}


    public static AdminRepo getRepositoryExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AdminRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public BoatDTO createBoat(String brand, String make, String name, String image) {
        EntityManager em  = getEntityManager();
        Boat newBoat = new Boat(brand, make, name,image, null, null);
        try {
            em.getTransaction().begin();
            em.persist(newBoat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(newBoat);
    }

    public void assignHarbourToBoat(int boatId, int harbourId){
        EntityManager em = getEntityManager();
        Harbour harbourToBeAssigned = em.find(Harbour.class, harbourId);
            if(harbourToBeAssigned == null)
                throw new EntityNotFoundException("The Harbour entity with ID: "+harbourId+" was not found");
        Boat boatToAssign = em.find(Boat.class, boatId);
            if(boatToAssign == null)
                throw new EntityNotFoundException("The Boat entity with ID: "+boatId+" was not found");
        boatToAssign.setHarbour(harbourToBeAssigned);

        try {
            em.getTransaction().begin();
            em.merge(boatToAssign);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public BoatDTO deleteBoat(int boatId){
        EntityManager em = getEntityManager();
        Boat boatToDelete = em.find(Boat.class, boatId);

        try {
            em.getTransaction().begin();
            em.remove(boatToDelete);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boatToDelete);
    }
}

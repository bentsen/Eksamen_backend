package repository;

import dtos.OwnerDTO;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepo {

    private static OwnerRepo instance;
    private static EntityManagerFactory emf;

    private OwnerRepo() {}


    public static OwnerRepo getRepositoryExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public OwnerDTO getById(int id) throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        Owner owner = em.find(Owner.class, id);
        if (owner == null)
            throw new EntityNotFoundException("The Owner entity with ID: "+id+" was not found");
        return new OwnerDTO(owner);
    }

    public List<OwnerDTO> getAllOwners() throws EntityNotFoundException {
        EntityManager em = getEntityManager();
        List<OwnerDTO> ownerDTOS = new ArrayList<>();

        try {
            TypedQuery<Owner> typedQuery = em.createQuery("SELECT owner FROM Owner owner", Owner.class);
            List<Owner> owners = typedQuery.getResultList();

            for (Owner owner : owners) {
                ownerDTOS.add(new OwnerDTO(owner));
            }
        } finally {
            em.close();
        }

        return ownerDTOS;
    }
}

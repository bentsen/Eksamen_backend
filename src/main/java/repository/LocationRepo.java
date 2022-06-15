package repository;

import dtos.LocationDTO;
import entities.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class LocationRepo implements ILocationRepo{

    private static LocationRepo instance;
    private static EntityManagerFactory emf;

    private LocationRepo(){}

    public static LocationRepo getRepo(EntityManagerFactory _emf){
        if(instance == null) {
            emf = _emf;
            instance = new LocationRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public void deleteLocation(int id) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        try {
            Location location = em.find(Location.class, id);
            if(location == null)
                System.out.println("location with provided id doesn't exist");
            em.getTransaction().begin();
            em.remove(location);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) throws IOException, URISyntaxException {
        Location location = new Location(locationDTO.getAddress(), locationDTO.getCity(), locationDTO.getCondition());
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new LocationDTO(location);
    }

    @Override
    public List<LocationDTO> getAllLocations() throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        List<Location> locations;

        TypedQuery<Location> query = em.createQuery("SELECT l from Location l", Location.class);
        locations = query.getResultList();

        return LocationDTO.getDtos(locations);
    }
}

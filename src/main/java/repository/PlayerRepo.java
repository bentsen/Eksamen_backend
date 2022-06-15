package repository;

import dtos.PlayerDTO;
import entities.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class PlayerRepo implements IPlayerRepo{

    private static PlayerRepo instance;
    private static EntityManagerFactory emf;

    private PlayerRepo(){}

    public static PlayerRepo getRepo(EntityManagerFactory _emf){
        if(instance == null) {
            emf = _emf;
            instance = new PlayerRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public List<PlayerDTO> getAllPlayers() throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        List<Player> players;

        TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p", Player.class);
        players = query.getResultList();

        return PlayerDTO.getDtos(players);
    }

    @Override
    public void deletePlayer(int id) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        try {
            Player player = em.find(Player.class, id);
            if(player == null)
                System.out.println("player with provided id doesn't exist");
            em.getTransaction().begin();
            em.remove(player);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) throws IOException, URISyntaxException {
        Player player = new Player(playerDTO.getName(),playerDTO.getPhone(),playerDTO.getEmail(),playerDTO.getStatus());
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new PlayerDTO(player);
    }
}

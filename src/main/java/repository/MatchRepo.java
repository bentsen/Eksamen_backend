package repository;

import dtos.MatchDTO;
import dtos.PlayerDTO;
import entities.Location;
import entities.Match;
import entities.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepo implements IMatchRepo{

    private static MatchRepo instance;
    private static EntityManagerFactory emf;

    private MatchRepo(){}

    public static MatchRepo getRepo(EntityManagerFactory _emf){
        if(instance == null) {
            emf = _emf;
            instance = new MatchRepo();
        }
        return instance;
    }

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public List<MatchDTO> getAllMatches() throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        List<Match> matches;

        TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m", Match.class);
        matches = query.getResultList();

        return MatchDTO.getDtos(matches);
    }

    @Override
    public List<MatchDTO> getMatchesFromPlayer(int id) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();

        Player player = em.find(Player.class, id);
        List<Match> matches = new ArrayList<>(player.getMatches());

        return MatchDTO.getDtos(matches);
    }

    @Override
    public List<MatchDTO> getMatchesFromLocation(int id) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();

        Location location = em.find(Location.class, id);
        List<Match> matches = new ArrayList<>(location.getMatches());

        return MatchDTO.getDtos(matches);
    }

    @Override
    public void deleteMatch(int id) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        try {
            Match match = em.find(Match.class, id);
            if(match == null)
                System.out.println("match with provided id doesn't exist");
            em.getTransaction().begin();
            em.remove(match);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDTO) throws IOException, URISyntaxException {
        Match match = new Match(matchDTO.getOpponentTeam(), matchDTO.getJudge(), matchDTO.getType(),matchDTO.isInDoors());
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(match);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new MatchDTO(match);
    }

    @Override
    public MatchDTO updateMatch(int id, MatchDTO matchDTO) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        try{
            Match match = em.find(Match.class, id);
            match.setJudge(matchDTO.getJudge());
            match.setOpponentTeam(matchDTO.getOpponentTeam());
            match.setType(matchDTO.getType());
            match.setInDoors(matchDTO.isInDoors());
            if(matchDTO.getLocationId() != 0)
            {
                Location location = em.find(Location.class, matchDTO.getLocationId());
                match.setLocation(location);
            }

            em.getTransaction().begin();
            em.merge(match);
            em.getTransaction().commit();
            return  new MatchDTO(match);
        } finally {
            em.close();
        }
    }

    @Override
    public MatchDTO updateAllMatches(int id, MatchDTO matchDTO) throws IOException, URISyntaxException {
        return null;
    }

    @Override
    public MatchDTO connectMatchToLocation(int matchId, int locationId) throws IOException, URISyntaxException {
        EntityManager em = getEntityManager();
        try{
            Match match = em.find(Match.class, matchId);
            Location location = em.find(Location.class, locationId);
            match.setLocation(location);

            em.getTransaction().begin();
            em.merge(match);
            em.getTransaction().commit();
            return new MatchDTO(match);
        } finally {
            em.close();
        }
    }
}

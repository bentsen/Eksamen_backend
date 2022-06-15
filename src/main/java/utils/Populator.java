package utils;

import entities.Location;
import entities.Match;
import entities.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            //Hvorfor i denne her rækkefølge? Her er en nem gennemgang
            //En mand skal have et sted at have sin båd, derfor findes havnen først
            //En mand skal eje en båd, derfor skal båden eksistere først
            //En mand kan derfor sætte sig selv som ejer til sidst

            //Create Harbour Entities
            Location location1 = new Location("BeanBeanStreet 42", "kastrup", "god");
            Location location2 = new Location("MeanMeanStreet 52", "glostrup", "god");
            Location location3 = new Location("SmeenSmeenStreet 62", "københavn", "god");
            //Store Harbour Entities
            em.persist(location1);
            em.persist(location2);
            em.persist(location3);
            //Create Boat Entities
            Match match1 = new Match("brøndby if", "hans", "fodbold", false);
            Match match2 = new Match("rødovre if", "hans", "fodbold", true);
            Match match3 = new Match("fck", "torben", "fodbold",  false);
            Match match4 = new Match("silkeborg fc", "christian", "fodbold", false);
            Match match5 = new Match("midtjylland", "oliver", "fodbold",  false);
            //Set Boat's harbour
            match1.setLocation(location1);
            match2.setLocation(location1);
            match3.setLocation(location2);
            match4.setLocation(location2);
            match5.setLocation(location3);
            //Store Boat Entities
            em.persist(match1);
            em.persist(match2);
            em.persist(match3);
            em.persist(match4);
            em.persist(match5);
            //Create Boat List1
            Set<Match> matches1 = new HashSet<>();
            matches1.add(match1);
            matches1.add(match2);
            //Create Boat List2
            Set<Match> matches2 = new HashSet<>();
            matches2.add(match3);
            matches2.add(match4);
            //Create Boat List3
            Set<Match> matches3 = new HashSet<>();
            matches3.add(match5);
            //Create Owner Entity
            Player player1 = new Player("Bean", 22268970, "bean@gmail.com", "active");
            Player player2 = new Player("BrotherBean", 26222879, "brotherBean@gmail.com", "active");
            Player player3 = new Player("SisterBean", 98702226, "sisterBean@gmail.com", "active");
            //Store Owners
            em.persist(player1);
            em.persist(player2);
            em.persist(player3);
            //Create Owner List1
            Set<Player> players1 = new HashSet<>();
            players1.add(player1);
            //Create Owner List2
            Set<Player> players2 = new HashSet<>();
            players2.add(player2);
            //Create Owner List3
            Set<Player> players3 = new HashSet<>();
            players3.add(player3);
            //Set Owners on Boats
            //StackOverFlow Debugging
            match1.setPlayers(players1);
            match2.setPlayers(players1);
            match3.setPlayers(players2);
            match4.setPlayers(players2);
            match5.setPlayers(players3);
            //Update Owners on Boats
            em.merge(match1);
            em.merge(match2);
            em.merge(match3);
            em.merge(match4);
            em.merge(match5);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("All is not good :(");
        } finally {
            //Save and commit changes to DB
            em.getTransaction().commit();
            em.close();
            emf.close();
        }

        System.out.println("all is good:)");
    }
}

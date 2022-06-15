package utils;

import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

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
            Harbour harbour1 = new Harbour(1, "BeanHarbour", "BeanBeanStreet 42", 10);
            Harbour harbour2 = new Harbour(2, "MeanHarbour", "MeanMeanStreet 52", 20);
            Harbour harbour3 = new Harbour(3, "SmeenHarbour", "SmeenSmeenStreet 62", 30);
            //Store Harbour Entities
            em.persist(harbour1);
            em.persist(harbour2);
            em.persist(harbour3);
            //Create Boat Entities
            Boat boat1 = new Boat(1, "Beancedes", "Turbo", "Rouladen", "url", null);
            Boat boat2 = new Boat(2, "Beanyota", "Cruiser", "Döneren", "url", null);
            Boat boat3 = new Boat(3, "BeanMW", "Sejl-Skib", "FrysePizzaen", "url", null);
            Boat boat4 = new Boat(4, "Beanrari", "4-Wheel-Bean", "Udentomat", "url", null);
            Boat boat5 = new Boat(5, "Beanmoghini", "Cruiser", "Jiggy", "url", null);
            //Set Boat's harbour
            boat1.setHarbour(harbour1);
            boat2.setHarbour(harbour1);
            boat3.setHarbour(harbour2);
            boat4.setHarbour(harbour3);
            boat5.setHarbour(harbour3);
            //Store Boat Entities
            em.persist(boat1);
            em.persist(boat2);
            em.persist(boat3);
            em.persist(boat4);
            em.persist(boat5);
            //Create Boat List1
            List<Boat> boatList1 = new ArrayList<>();
            boatList1.add(boat1);
            boatList1.add(boat2);
            //Create Boat List2
            List<Boat> boatList2 = new ArrayList<>();
            boatList2.add(boat3);
            boatList2.add(boat4);
            //Create Boat List3
            List<Boat> boatList3 = new ArrayList<>();
            boatList3.add(boat5);
            //Create Owner Entity
            Owner owner1 = new Owner(1, "Bean", "Beanvej", 22505080, boatList1);
            Owner owner2 = new Owner(2, "BrotherBean", "Beanvej", 22505081, boatList2);
            Owner owner3 = new Owner(3, "SisterBean", "Beanvej", 22505082, boatList3);
            //Store Owners
            em.persist(owner1);
            em.persist(owner2);
            em.persist(owner3);
            //Create Owner List1
            List<Owner> ownerList1 = new ArrayList<>();
            ownerList1.add(owner1);
            //Create Owner List2
            List<Owner> ownerList2 = new ArrayList<>();
            ownerList2.add(owner2);
            //Create Owner List3
            List<Owner> ownerList3 = new ArrayList<>();
            ownerList3.add(owner3);
            //Set Owners on Boats
            //StackOverFlow Debugging
            boat1.setOwners(ownerList1);
            boat2.setOwners(ownerList1);
            boat3.setOwners(ownerList2);
            boat4.setOwners(ownerList2);
            boat5.setOwners(ownerList3);
            //Update Owners on Boats
            em.merge(boat1);
            em.merge(boat2);
            em.merge(boat3);
            em.merge(boat4);
            em.merge(boat5);
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

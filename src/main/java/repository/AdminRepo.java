package repository;

import javax.persistence.*;

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


}

package fu.DE201056.dao;

import fu.DE201056.pojo.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class ProjectDAO {

    private EntityManagerFactory emf;

    public void save(Project p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

}

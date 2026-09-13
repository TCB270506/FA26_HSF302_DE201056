package fu.DE201056.dao;

import fu.DE201056.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hsf301FU");

    // create todo3
    public void save(Employee e){
        EntityManager em= emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        }finally{
            em.close();
        }
    }

}

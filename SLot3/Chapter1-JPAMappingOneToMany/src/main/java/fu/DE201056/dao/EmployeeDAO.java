package fu.DE201056.dao;

import fu.DE201056.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hsf302FU");

   
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


    public Employee findById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return  em.find(Employee.class, id);
        }finally{
            em.close();
        }
    }

    public List<Employee> findAll(){
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
        }finally{
            em.close();
        }
    }


    public Employee findByEmail(String email){
        EntityManager em = emf.createEntityManager();
        try{
            List<Employee> result = em.createQuery("SELECT e FROM  Employee e WHERE e.email = :email", Employee.class)
                    .setParameter("email", email)
                    .getResultList();
            return  result.isEmpty()? null: result.get(0);
        }finally {
            em.close();
        }
    }

    public List<Employee> findBySalaryGreaterThanAndActive(BigDecimal minSalary){
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT e FROM Employee e WHERE e.salary > :minSalary AND e.active = true", Employee.class)
                    .setParameter("minSalary", minSalary)
                    .getResultList();
        }finally{
            em.close();
        }
    }


    public  Employee update(Employee e){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Employee merged = em.merge(e);
            em.getTransaction().commit();
            return merged;
        }catch (RuntimeException ex){
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        }finally {
            em.close();
        }
    }


    public void delete(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Employee e = em .find(Employee.class, id);
            if(e != null){
                em.remove(e);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }
}

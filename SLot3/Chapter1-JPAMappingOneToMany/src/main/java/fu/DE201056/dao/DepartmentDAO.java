package fu.DE201056.dao;

import fu.DE201056.pojo.Department;
import fu.DE201056.pojo.Employee;
import fu.DE201056.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DepartmentDAO {

    private final EntityManagerFactory emf = JPAUtil.getEMF();

    // save department
    public void save(Department d) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(d); // cascade=ALL sẽ tự persist các Employee đã add

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    // time department theo id
    public Department findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Department.class, id);
        } catch (RuntimeException ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    //1 Department kèm danh sách Employee trong 1 lần
    public Department findAllEmployeeInDepartment(Long id){
        EntityManager em = emf.createEntityManager();
        Department department = em.createQuery("SELECT d FROM Department d JOIN FETCH d.employees where d.id= :id", Department.class)
                .setParameter("id",id)
                .getSingleResult();
        em.close();

        return department;
    }

    //find all
    public List<Department> findAllDepartmentsAndEmployees() {
        EntityManager em = emf.createEntityManager();
        List<Department> departments = null;

        try {
            departments = em.createQuery(
                    "SELECT d FROM Department d JOIN FETCH d.employees",
                    Department.class
            ).getResultList();
        } finally {
            em.close();
        }
        return departments;
    }

}
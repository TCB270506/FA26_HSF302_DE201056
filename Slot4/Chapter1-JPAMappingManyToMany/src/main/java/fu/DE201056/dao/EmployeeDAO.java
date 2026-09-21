package fu.DE201056.dao;

import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class EmployeeDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hsf302FU");


    public void save(Employee e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void assignEmployeeToProject(Long employeeId, Long projectId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, employeeId);
            Project project = em.find(Project.class, projectId);

            if (employee == null || project == null) {
                throw new IllegalArgumentException("Employee or Project not found");
            }

            employee.assignToProject(project);

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

    public void unassignEmployeeFromProject(Long employeeId, Long projectId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, employeeId);
            Project project = em.find(Project.class, projectId);

            if (employee == null || project == null) {
                throw new IllegalArgumentException("Employee or Project not found");
            }

            employee.unassignFromProject(project);

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

    public List<Object[]> CountSalaryOfActiveEmployee(){

        EntityManager em = emf.createEntityManager();
        List<Object[]> list;
        try {
            list = em.createQuery("SELECT p.projectName, COUNT(e), SUM(e.salary)\n" +
                    "FROM Project p JOIN p.employees e\n" +
                    "WHERE e.active = true\n" +
                    "GROUP BY p.projectName\n", Object[].class).getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }finally {
            emf.close();
        }
        return list;
    }

}
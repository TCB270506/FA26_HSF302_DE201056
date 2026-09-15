package fu.DE201056;

import fu.DE201056.pojo.Department;
import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

Department dept = new Department("IT", "Ha Noi");
        Employee emp = new Employee("Test", "test@company.com", Gender.OTHER,
                new BigDecimal("1000"), LocalDate.now());
        dept.addEmployee(emp);
        System.out.println(dept.getEmployees().contains(emp)); // phải true
        System.out.println(emp.getDepartment() == dept); // phải true

    }
}
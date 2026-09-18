package fu.DE201056;

import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.dao.ProjectDAO;
import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Gender;
import fu.DE201056.pojo.Project;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ProjectDAO projectDAO = new ProjectDAO();

        // 1. Create 3 Employees
        Employee e1 = new Employee(
                "Nguyen Van A",
                "a@gmail.com",
                new BigDecimal("1500.00"),
                Gender.MALE,
                LocalDate.of(2023, 1, 10)
        );

        Employee e2 = new Employee(
                "Tran Thi B",
                "b@gmail.com",
                new BigDecimal("1800.00"),
                Gender.FEMALE,
                LocalDate.of(2023, 5, 15)
        );

        Employee e3 = new Employee(
                "Le Van C",
                "c@gmail.com",
                new BigDecimal("2000.00"),
                Gender.MALE,
                LocalDate.of(2024, 2, 20)
        );

        // active mặc định = true
        e1.setActive(true);
        e2.setActive(true);
        e3.setActive(true);

        // 2. Create 2 Projects
        Project p1 = new Project(
                "PRJ001",
                "Hospital Management",
                new BigDecimal("50000.00"),
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 12, 31)
        );

        Project p2 = new Project(
                "PRJ002",
                "Healthcare System",
                new BigDecimal("80000.00"),
                LocalDate.of(2025, 3, 1),
                null
        );

        // 3. Save Employees and Projects
        employeeDAO.save(e1);
        employeeDAO.save(e2);
        employeeDAO.save(e3);

        projectDAO.save(p1);
        projectDAO.save(p2);

        // 4. Assign employees to projects
        // NV1 -> Project A + B
        employeeDAO.assignEmployeeToProject(e1.getId(), p1.getId());
        employeeDAO.assignEmployeeToProject(e1.getId(), p2.getId());

        // NV2 -> Project B
        employeeDAO.assignEmployeeToProject(e2.getId(), p2.getId());

        // NV3 -> Project A
        employeeDAO.assignEmployeeToProject(e3.getId(), p1.getId());

        // 5. Print projects of each employee
        System.out.println("Employee 1 projects:");
        for (Project p : e1.getProjects()) {
            System.out.println(p.getProjectCode() + " - " + p.getProjectName());
        }

        System.out.println("\nEmployee 2 projects:");
        for (Project p : e2.getProjects()) {
            System.out.println(p.getProjectCode() + " - " + p.getProjectName());
        }

        System.out.println("\nEmployee 3 projects:");
        for (Project p : e3.getProjects()) {
            System.out.println(p.getProjectCode() + " - " + p.getProjectName());
        }
    }
}
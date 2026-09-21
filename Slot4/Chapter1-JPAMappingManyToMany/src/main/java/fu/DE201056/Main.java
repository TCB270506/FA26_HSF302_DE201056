
package fu.DE201056;

import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.pojo.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        while (true) {

            System.out.println("\n========== EMPLOYEE MENU ==========");
            System.out.println("1. Assign Employee to Project");
            System.out.println("2. Unassign Employee from Project");
            System.out.println("3. Find Active Employees in > 1 Project");
            System.out.println("4. Count Active Employees & Sum Salary by Project");
            System.out.println("5. Deactivate Employee & Remove from All Projects");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter Employee ID: ");
                        Long employeeId = sc.nextLong();

                        System.out.print("Enter Project ID: ");
                        Long projectId = sc.nextLong();

                        employeeDAO.assignEmployeeToProject(employeeId, projectId);

                        System.out.println("Employee assigned successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Employee ID: ");
                        employeeId = sc.nextLong();

                        System.out.print("Enter Project ID: ");
                        projectId = sc.nextLong();

                        employeeDAO.unassignEmployeeFromProject(employeeId, projectId);

                        System.out.println("Employee unassigned successfully!");
                        break;

                    case 3:
                        List<Employee> employees =
                                employeeDAO.countActiveEmployee();

                        System.out.println("\nActive employees in more than 1 project:");

                        for (Employee e : employees) {
                            System.out.println(
                                    "ID: " + e.getId()
                                            + " | Name: " + e.getFullName()
                                            + " | Email: " + e.getEmail()
                            );
                        }

                        System.out.println("Total: " + employees.size());
                        break;

                    case 4:
                        List<Object[]> results =
                                employeeDAO.countSalaryOfActiveEmployee();

                        System.out.println("\nProject | Active Employees | Total Salary");

                        for (Object[] row : results) {
                            System.out.println(
                                    row[0] + " | "
                                            + row[1] + " | "
                                            + row[2]
                            );
                        }
                        break;

                    case 5:
                        System.out.print("Enter Employee ID: ");
                        employeeId = sc.nextLong();

                        employeeDAO.deactivateEmployee(employeeId);

                        System.out.println(
                                "Employee deactivated and removed from all projects!"
                        );
                        break;

                    case 0:
                        System.out.println("Program ended.");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");

                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}


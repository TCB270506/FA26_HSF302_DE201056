package fu.DE201056;

import fu.DE201056.dao.DepartmentDAO;
import fu.DE201056.pojo.Department;
import fu.DE201056.pojo.Employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DepartmentDAO dao = new DepartmentDAO();


        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Find Department and Employees");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Department ID: ");
                    Long id = sc.nextLong();

                    Department department = dao.findAllEmployeeInDepartment(id);

                    System.out.println("Department: " + department.getName());

                    for (Employee employee : department.getEmployees()) {
                        System.out.println(
                                "Employee ID: " + employee.getId()
                                        + ", Name: " + employee.getFullName()
                        );
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}









package fu.DE201056;

import fu.DE201056.dao.DepartmentDAO;
import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.pojo.Department;
import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DepartmentDAO departmentDAO = new DepartmentDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();


        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Find Department and Employees");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter employee email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter employee salary: ");
                    BigDecimal salary = sc.nextBigDecimal();

                    System.out.print("Enter employee gender (Male/Female): ");
                    Gender gender = Gender.valueOf(sc.next());

                    System.out.print("Enter hire year: ");
                    int year = sc.nextInt();

                    System.out.print("Enter hire month: ");
                    int month = sc.nextInt();

                    System.out.print("Enter hire day: ");
                    int day = sc.nextInt();

                    System.out.print("Enter Department ID: ");
                    Long departmentId = sc.nextLong();


                    Department department = departmentDAO.findById(departmentId);
                    if (department == null) {
                        System.out.println("Department not found!");
                        break;
                    }

                    LocalDate hireDate = LocalDate.of(year, month, day);

                    Employee employee = new Employee(
                            name,
                            email,
                            salary,
                            gender,
                            hireDate
                    );

                    employee.setDepartment(department);

                    employeeDAO.save(employee);

                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Department ID: ");
                    Long id = sc.nextLong();

                    department = departmentDAO.findAllEmployeeInDepartment(id);

                    System.out.println("Department: " + department.getName());

                    System.out.println("Employees:");

                    for (Employee emp : department.getEmployees()) {
                        System.out.println(
                                "ID: " + emp.getId()
                                        + ", Name: " + emp.getFullName()
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









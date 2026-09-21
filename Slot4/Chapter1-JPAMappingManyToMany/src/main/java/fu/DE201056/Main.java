
package fu.DE201056;

import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.pojo.Employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        List<Employee> employees = dao.countActiveEmployee();

        System.out.println("Active employees in more than 1 project:");

        for (Employee e : employees) {
            System.out.println(
                    e.getId() + " | " +
                            e.getFullName() + " | " +
                            e.getEmail() + " | " +
                            e.isActive()
            );
        }
    }
}


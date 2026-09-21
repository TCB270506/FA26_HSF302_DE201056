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

        employeeDAO.setActive(9L,true);

    }
}
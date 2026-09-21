package fu.DE201056;

import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.dao.ProjectDAO;
import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Gender;
import fu.DE201056.pojo.Project;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        List<Object[]> list = dao.CountSalaryOfActiveEmployee();

        for (Object[] row : list) {
            System.out.println(row[0] + " | " + row[1] + " | " + row[2]);
        }
    }
}
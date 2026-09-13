import fu.DE201056.dao.EmployeeDAO;
import fu.DE201056.pojo.Employee;
import fu.DE201056.pojo.Gender;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        //save
        Employee emp = new Employee("Nguyen Van A","a@fpt.edu.vn",new BigDecimal("15000000"), Gender.MALE, LocalDate.of(2022, 3, 1));
        dao.save(emp);
        System.out.println("Da tao: "+ emp);

        //read
        Employee found = dao.findById(emp.getId());
        System.out.println("Doc lai: "+found);

        //update
        found.setSalary(new BigDecimal("17000000"));
        Employee updated = dao.update(found);
        System.out.println("Sau update: "+updated);

        Employee reChecked = dao.findById(emp.getId());
        System.out.println("Kiem tra lai sau update"+reChecked);

        //delete
        dao.delete(emp.getId());
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("Sau khi xoa, tim lai: "+afterDelete);


    }
}

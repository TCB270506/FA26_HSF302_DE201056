package fu.DE201056.Chapter6_Slot9_Demo.service;

import fu.DE201056.Chapter6_Slot9_Demo.dto.StudentForm;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Major;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Page<Student> findAll(Pageable pageable);

    Page<Student> search(String keyword, Pageable pageable);

    Optional<Student> findById(Long id);

    Optional<StudentForm> findFormById(Long id);

    Student create(StudentForm form);

    boolean update(Long id, StudentForm form);

    boolean delete(Long id);

    boolean isEmailTaken(String email, Long excludeId);

    List<Major> getMajors();
}

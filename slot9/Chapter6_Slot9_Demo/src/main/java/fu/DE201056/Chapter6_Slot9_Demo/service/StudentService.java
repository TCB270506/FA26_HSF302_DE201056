package fu.DE201056.Chapter6_Slot9_Demo.service;

import fu.DE201056.Chapter6_Slot9_Demo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Page<Student> findAll(Pageable pageable);

    Optional<Student> findById(Long id);

    Student create(Student student);

    /** @return true nếu tìm thấy và cập nhật; false nếu không tồn tại id */
    boolean update(Long id, Student data);

    /** @return true nếu xoá được; false nếu không tồn tại id */
    boolean delete(Long id);

    /** Kiểm tra email trùng. excludeId = null khi thêm mới, = id hiện tại khi cập nhật */
    boolean isEmailTaken(String email, Long excludeId);

    List<String> getMajors();

    Page<Student> search(String keyword, Pageable pageable);
}

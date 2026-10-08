package fu.DE201056.Chapter6_Slot9_Demo.service;


import fu.DE201056.Chapter6_Slot9_Demo.dto.StudentForm;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Student;
import fu.DE201056.Chapter6_Slot9_Demo.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<StudentForm> findFormById(Long id) {
        return studentRepository.findById(id).map(this::toForm);
    }

    @Override
    @Transactional
    public Student create(StudentForm form) {
        Student student = toEntity(form);
        student.setId(null);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public boolean update(Long id, StudentForm form) {
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setName(form.getName());
                    existing.setEmail(form.getEmail());
                    existing.setAge(form.getAge());
                    existing.setMajor(form.getMajor());
                    existing.setGpa(form.getGpa());
                    return true;
                })
                .orElse(false);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean isEmailTaken(String email, Long excludeId) {
        if (email == null || email.isBlank()) return false;
        return excludeId == null
                ? studentRepository.existsByEmailIgnoreCase(email.trim())
                : studentRepository.existsByEmailIgnoreCaseAndIdNot(email.trim(), excludeId);
    }

    @Override
    public List<String> getMajors() {
        return List.of("CNTT", "KTPM", "HTTT", "ATTT", "MMT");
    }

    @Override
    public Page<Student> search(String keyword, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
    }

    private StudentForm toForm(Student student) {
        StudentForm form = new StudentForm();
        form.setId(student.getId());
        form.setName(student.getName());
        form.setEmail(student.getEmail());
        form.setAge(student.getAge());
        form.setMajor(student.getMajor());
        form.setGpa(student.getGpa());
        return form;
    }

    private Student toEntity(StudentForm form) {
        Student student = new Student();
        student.setId(form.getId());
        student.setName(form.getName());
        student.setEmail(form.getEmail());
        student.setAge(form.getAge());
        student.setMajor(form.getMajor());
        student.setGpa(form.getGpa());
        return student;
    }
}
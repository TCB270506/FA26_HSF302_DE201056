package fu.DE201056.Chapter6_Slot9_Demo.service;


import fu.DE201056.Chapter6_Slot9_Demo.dto.StudentForm;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Major;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Student;
import fu.DE201056.Chapter6_Slot9_Demo.repository.MajorRepository;
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
    private final MajorRepository majorRepository;

    public StudentServiceImpl(StudentRepository studentRepository, MajorRepository majorRepository) {
        this.studentRepository = studentRepository;
        this.majorRepository = majorRepository;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> search(String keyword, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
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
    public List<Major> getMajors() {
        return majorRepository.findAll();
    }

    private StudentForm toForm(Student student) {
        StudentForm form = new StudentForm();
        form.setId(student.getId());
        form.setName(student.getName());
        form.setEmail(student.getEmail());
        form.setAge(student.getAge());
        form.setGpa(student.getGpa());
        if (student.getMajor() != null) {
            form.setMajorName(student.getMajor().getName());
        }
        return form;
    }

    private Student toEntity(StudentForm form) {
        Student student = new Student();
        student.setId(form.getId());
        student.setName(form.getName());
        student.setEmail(form.getEmail());
        student.setAge(form.getAge());
        student.setGpa(form.getGpa());
        if (form.getMajorName() != null) {
            majorRepository.findByName(form.getMajorName()).ifPresent(student::setMajor);
        }
        return student;
    }

    @Override
    @Transactional
    public boolean update(Long id, StudentForm form) {
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setName(form.getName());
                    existing.setEmail(form.getEmail());
                    existing.setAge(form.getAge());
                    existing.setGpa(form.getGpa());
                    if (form.getMajorName() != null) {
                        majorRepository.findByName(form.getMajorName()).ifPresent(existing::setMajor);
                    }
                    return true;
                })
                .orElse(false);
    }
}
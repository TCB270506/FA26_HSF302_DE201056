package fu.DE201056.Chapter6_Slot9_Demo.config;

import fu.DE201056.Chapter6_Slot9_Demo.entity.Major;
import fu.DE201056.Chapter6_Slot9_Demo.entity.Student;
import fu.DE201056.Chapter6_Slot9_Demo.repository.MajorRepository;
import fu.DE201056.Chapter6_Slot9_Demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;

    public DataInitializer(StudentRepository studentRepository, MajorRepository majorRepository) {
        this.studentRepository = studentRepository;
        this.majorRepository = majorRepository;
    }

    @Override
    public void run(String... args) {
        if (majorRepository.count() == 0) {
            majorRepository.saveAllAndFlush(List.of(
                    new Major("CNTT"),
                    new Major("KTPM"),
                    new Major("ATTT"),
                    new Major("HTTT"),
                    new Major("MMT")
            ));
        }

        Map<String, Major> majorMap = majorRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Major::getName, m -> m));

        if (studentRepository.count() > 0) {
            log.info("Bảng students đã có dữ liệu → bỏ qua seed");
            return;
        }

        studentRepository.saveAll(List.of(
                new Student("Nguyễn Văn An",  "an@fpt.edu.vn",    20, majorMap.get("CNTT"), 3.5),
                new Student("Trần Thị Bình",  "binh@fpt.edu.vn",  21, majorMap.get("KTPM"), 3.2),
                new Student("Lê Minh Cường",  "cuong@fpt.edu.vn", 19, majorMap.get("ATTT"), 3.8),
                new Student("Phạm Thị Dung",  "dung@fpt.edu.vn",  22, majorMap.get("HTTT"), 2.9)
        ));
        log.info("Đã seed {} sinh viên", studentRepository.count());
    }
}
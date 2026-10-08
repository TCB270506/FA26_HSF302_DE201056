package fu.DE201056.Chapter6_Slot9_Demo.repository;

import fu.DE201056.Chapter6_Slot9_Demo.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Optional<Major> findByName(String name);
}
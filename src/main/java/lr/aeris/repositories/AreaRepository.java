package lr.aeris.repositories;

import lr.aeris.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findByFullNameContainingIgnoreCase(String name);

    Optional<Area> findByTag(String tag);
}

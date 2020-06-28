package lr.aeris.repositories;

import lr.aeris.model.SpawnArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnAreaRepository extends JpaRepository<SpawnArea, String> {

    List<SpawnArea> findByTagContainingIgnoreCase(String tag);
}

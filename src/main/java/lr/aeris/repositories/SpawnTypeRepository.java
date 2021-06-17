package lr.aeris.repositories;

import lr.aeris.model.SpawnType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnTypeRepository extends JpaRepository<SpawnType, String> {
    List<SpawnType> findByTypeNotIn(List<String> list);

    List<SpawnType> findByTypeContainingIgnoreCase(String typeName);
}

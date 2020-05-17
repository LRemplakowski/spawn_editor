package lr.aeris.repositories;

import lr.aeris.model.SpawnType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnTypeRepository extends JpaRepository<SpawnType, String> {

    @Query(value = "SELECT t FROM SpawnType t WHERE t.type=:type")
    List<SpawnType> findByType(String type);
}

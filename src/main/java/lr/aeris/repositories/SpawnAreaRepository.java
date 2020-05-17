package lr.aeris.repositories;

import lr.aeris.model.SpawnArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnAreaRepository extends JpaRepository<SpawnArea, String> {

    @Query(value = "SELECT a FROM SpawnArea a WHERE a.areatag=:areatag")
    List<SpawnArea> findByAreatag(String areatag);

    List<SpawnArea> findByAreatagContainingIgnoreCase(String areatag);
}

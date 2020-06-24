package lr.aeris.repositories;

import lr.aeris.model.SpawnPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnPairRepository extends JpaRepository<SpawnPair, String> {

    @Query(value = "SELECT p FROM SpawnPair p WHERE p.resref=:resref")
    List<SpawnPair> findByResref(String resref);

    List<SpawnPair> findByResrefNotIn(List<String> list);
}

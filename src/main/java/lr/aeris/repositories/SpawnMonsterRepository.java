package lr.aeris.repositories;

import lr.aeris.model.SpawnMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnMonsterRepository extends JpaRepository<SpawnMonster, String> {

    @Query(value = "SELECT m FROM SpawnMonster m WHERE m.resref=:resref")
    List<SpawnMonster> findByResref(String resref);
}

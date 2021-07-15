package lr.aeris.repositories;

import lr.aeris.model.SpawnArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnAreaRepository extends JpaRepository<SpawnArea, String> {

    List<SpawnArea> findByTagContainingIgnoreCase(String tag);

    @Query(value = "SELECT a FROM SpawnArea a WHERE a.tag IN " +
                "(SELECT r.areatag FROM SpawnRule r WHERE r.rule IN " +
                    "(SELECT p.type FROM SpawnPair p WHERE p.resref IN " +
                        "(SELECT m.resref FROM SpawnMonster m " +
                            "WHERE m.name LIKE :monsterName AND m.resref LIKE :monsterResref AND m.cr BETWEEN a.crmin AND a.crmax)))")
    List<SpawnArea> findSpawnAreasByMonsterNameAndResref(String monsterName, String monsterResref);
}

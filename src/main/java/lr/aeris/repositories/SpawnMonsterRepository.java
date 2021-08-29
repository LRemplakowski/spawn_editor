package lr.aeris.repositories;

import lr.aeris.model.SpawnMonster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnMonsterRepository extends JpaRepository<SpawnMonster, String> {

    @Query(value = "SELECT m FROM SpawnMonster m ORDER BY m.name")
    List<SpawnMonster> findAllOrdered();

    List<SpawnMonster> findByResrefOrderByName(String resref);

    List<SpawnMonster> findByResrefContainingIgnoreCaseOrderByName(String resref);

    @Query(value = "SELECT m FROM SpawnMonster m WHERE m.resref IN " +
            "(SELECT p.resref FROM SpawnPair p WHERE p.type IN " +
            "(SELECT r.rule FROM SpawnRule r WHERE r.areatag IN " +
            "(SELECT a.tag FROM SpawnArea a WHERE a.tag LIKE :areaTag AND " +
            "m.cr BETWEEN a.crmin AND a.crmax AND a.tag IN " +
            "(SELECT aa.tag FROM Area aa WHERE aa.fullName LIKE :areaName)))) " +
            "ORDER BY m.name")
    List<SpawnMonster> findByAreaNameAndTag(String areaName, String areaTag);

    @Query(value = "SELECT m FROM SpawnMonster m WHERE m.resref NOT IN " +
            "(SELECT p.resref FROM SpawnPair p WHERE p.type IN " +
            "(SELECT r.rule FROM SpawnRule r WHERE r.areatag IN " +
            "(SELECT a.tag FROM SpawnArea a WHERE m.cr BETWEEN a.crmin AND a.crmax))) " +
            "ORDER BY m.name")
    List<SpawnMonster> findAllWithNoSpawnArea();
}

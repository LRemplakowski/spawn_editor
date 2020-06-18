package lr.aeris.repositories;

import lr.aeris.model.SpawnRule;
import lr.aeris.model.SpawnType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpawnRuleRepository extends JpaRepository<SpawnRule, String> {

    @Query(value = "SELECT r FROM SpawnRule r WHERE r.rule=:rule AND r.areatag=:areatag")
    List<SpawnRule> findByRuleAndAreatag(String rule, String areatag);

    @Query(value = "SELECT r FROM SpawnRule r WHERE r.areatag=:areatag")
    List<SpawnRule> findByAreatag(String areatag);
}

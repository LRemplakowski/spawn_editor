package lr.aeris.service;

import lr.aeris.model.SpawnRule;
import lr.aeris.repositories.SpawnRuleRepository;
import lr.aeris.requests.ChangeAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpawnRuleService {
    private final SpawnRuleRepository repository;

    @Autowired
    public SpawnRuleService(SpawnRuleRepository repository) {
        this.repository = repository;
    }

    public List<String> findRulesByAreatag(String areatag){
        List<String> result = new ArrayList<>();
        repository.findByAreatag(areatag).forEach(r -> result.add(r.getRule()));
        return result;
    }

    @Transactional
    public void saveEditedSpawnRules(ChangeAreaRequest request){
        String areatag = request.getTag();
        List<SpawnRule> originalRules = repository.findByAreatag(areatag);
        repository.deleteAll(originalRules);
        List<SpawnRule> newRules = new ArrayList<>();
        request.getSpawnRules().forEach(r -> newRules.add(new SpawnRule(r, areatag)));
        if(!newRules.isEmpty()){
            repository.saveAll(newRules);
        }
    }
}

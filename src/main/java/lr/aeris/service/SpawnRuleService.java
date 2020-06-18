package lr.aeris.service;

import lr.aeris.model.SpawnRule;
import lr.aeris.repositories.SpawnRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

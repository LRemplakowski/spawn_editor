package lr.aeris.service;

import lr.aeris.model.SpawnType;
import lr.aeris.repositories.SpawnTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpawnTypeService {
    private final SpawnTypeRepository repository;

    @Autowired
    public SpawnTypeService(SpawnTypeRepository repository) {
        this.repository = repository;
    }

    public List<String> findTypesOtherThan(List<String> types){
        List<String> result = new ArrayList<>();
        if(!types.isEmpty()){
            repository.findByTypeNotIn(types).forEach(t -> result.add(t.getType()));
        } else {
            repository.findAll().forEach(t -> result.add(t.getType()));
        }
        return result;
    }
}

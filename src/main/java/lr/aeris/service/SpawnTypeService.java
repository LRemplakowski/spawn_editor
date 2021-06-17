package lr.aeris.service;

import lr.aeris.model.SpawnType;
import lr.aeris.repositories.SpawnTypeRepository;
import lr.aeris.requests.AddTypeRequest;
import lr.aeris.requests.DeleteTypeRequest;
import lr.aeris.requests.SpawnTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpawnTypeService {
    private final SpawnTypeRepository repository;

    @Autowired
    public SpawnTypeService(SpawnTypeRepository repository) {
        this.repository = repository;
    }

    public List<String> findTypeNamesOtherThan(List<String> types){
        List<String> result = new ArrayList<>();
        if(!types.isEmpty()){
            repository.findByTypeNotIn(types).forEach(t -> result.add(t.getType()));
        } else {
            repository.findAll().forEach(t -> result.add(t.getType()));
        }
        return result;
    }

    public List<SpawnType> findAllTypes(){
        return repository.findAll();
    }

    public SpawnType findTypeById(String id){
        Optional<SpawnType> result = repository.findById(id);
        return result.orElse(null);
    }

    public List<SpawnType> findTypesByQuery(SpawnTypeRequest request) {
        return repository.findByTypeContainingIgnoreCase(request.getTypeName());
    }

    @Transactional
    public void saveNewType(AddTypeRequest request) {
        Optional<SpawnType> found = repository.findById(request.getTypeId());
        if(!found.isPresent()) {
            SpawnType t = new SpawnType(request.getTypeId());
            repository.save(t);
        }
    }

    @Transactional
    public void deleteType(DeleteTypeRequest request) {
        Optional<SpawnType> found = repository.findById(request.getTypeId());
        found.ifPresent(repository::delete);
    }
}

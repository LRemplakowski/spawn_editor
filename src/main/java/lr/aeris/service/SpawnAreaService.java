package lr.aeris.service;

import lr.aeris.model.SpawnArea;
import lr.aeris.repositories.SpawnAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpawnAreaService {
    private final SpawnAreaRepository repository;

    @Autowired
    public SpawnAreaService(SpawnAreaRepository repository){
        this.repository = repository;
    }

    public List<SpawnArea> findAllAreas(){
        return repository.findAll();
    }
}

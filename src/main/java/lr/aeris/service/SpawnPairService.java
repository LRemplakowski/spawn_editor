package lr.aeris.service;

import lr.aeris.repositories.SpawnPairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpawnPairService {
    private final SpawnPairRepository repository;

    @Autowired
    public SpawnPairService(SpawnPairRepository repository) {
        this.repository = repository;
    }
}

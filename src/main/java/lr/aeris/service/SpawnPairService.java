package lr.aeris.service;

import lr.aeris.model.SpawnPair;
import lr.aeris.repositories.SpawnPairRepository;
import lr.aeris.requests.AddSpawnPairsRequest;
import lr.aeris.requests.ChangeMonsterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpawnPairService {
    private final SpawnPairRepository repository;

    @Autowired
    public SpawnPairService(SpawnPairRepository repository) {
        this.repository = repository;
    }

    public List<String> getPairTypesByResref(String resref) {
        List<String> result = new ArrayList<>();
        repository.findByResref(resref).forEach(p -> result.add(p.getType()));
        return result;
    }

    public List<SpawnPair> getPairsByResRef(String resref) {
        return repository.findByResref(resref);
    }

    public List<SpawnPair> getPairsByType(String type)
    {
        return repository.findByType(type);
    }

    public List<String> getResrefsPairedWithType(String type)
    {
        List<SpawnPair> pairs = repository.findByType(type);
        List<String> resrefs = new ArrayList<>();
        pairs.forEach(p -> resrefs.add(p.getResref()));
        return  resrefs;
    }

    @Transactional
    public void saveEditedSpawnPairs(ChangeMonsterRequest request) {
        String resref = request.getResref();
        List<SpawnPair> originalPairs = repository.findByResref(resref);
        repository.deleteAll(originalPairs);
        List<SpawnPair> newPairs = new ArrayList<>();
        request.getSpawnPairs().forEach(p -> newPairs.add(new SpawnPair(resref, p)));
        repository.saveAll(newPairs);
    }

    @Transactional
    public boolean addSpawnPairs(AddSpawnPairsRequest request) {
        String resref = request.getResref();
        List<SpawnPair> existingPairs = repository.findByResref(resref);
        if (!existingPairs.isEmpty())
            return false;
        List<SpawnPair> newPairs = new ArrayList<>();
        request.getSpawnTypes().forEach(t -> newPairs.add(new SpawnPair(resref, t.getType())));
        repository.saveAll(newPairs);
        return true;
    }
}

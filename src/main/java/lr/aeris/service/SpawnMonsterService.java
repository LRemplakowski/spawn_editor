package lr.aeris.service;

import lr.aeris.model.SpawnMonster;
import lr.aeris.model.SpawnPair;
import lr.aeris.model.SpawnRule;
import lr.aeris.model.SpawnType;
import lr.aeris.repositories.SpawnMonsterRepository;
import lr.aeris.requests.AddMonsterRequest;
import lr.aeris.requests.ChangeMonsterRequest;
import lr.aeris.requests.SpawnMonsterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpawnMonsterService {

    private final SpawnMonsterRepository repository;
    private final SpawnPairService spawnPairService;

    @Autowired
    public SpawnMonsterService(SpawnMonsterRepository repository, SpawnPairService spawnPairService){
        this.repository = repository;
        this.spawnPairService = spawnPairService;
    }

    public List<SpawnMonster> findAllMonsters(){
        return repository.findAll();
    }

    public List<SpawnMonster> findMonstersMatchingQuery(SpawnMonsterRequest request){
        List<SpawnMonster> result;
        if(!request.getResref().isEmpty()){
            result = repository.findByResrefContainingIgnoreCase(request.getResref());
        } else {
            result = repository.findAll();
        }

        List<String> resrefsPairedWithType = new ArrayList<>();
        if(!request.getBaseType().getType().isEmpty())
        {
            resrefsPairedWithType = spawnPairService.getResrefsPairedWithType(request.getBaseType().getType());
        }
        List<String> finalResrefsPairedWithType = resrefsPairedWithType;

        result = result.stream()
                .filter(r -> r.getName().toLowerCase().contains(request.getName().toLowerCase()))
                .filter(r -> request.getCr() <= -1 || r.getCr().equals(request.getCr()))
                .filter(r -> request.getBaseType().getType().isEmpty() || finalResrefsPairedWithType.contains(r.getResref()))
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void saveEditedMonster(ChangeMonsterRequest request) {
        Optional<SpawnMonster> found = repository.findById(request.getResref());
        if(found.isPresent()) {
            SpawnMonster monster = found.get();
            monster.setCr(request.getCr() != null ? request.getCr() : monster.getCr());
            monster.setBaseType(request.getBaseType() != null ? request.getBaseType() : monster.getBaseType());
            repository.save(monster);
        }
    }

    public boolean addNewMonster(AddMonsterRequest request) {
        Optional<SpawnMonster> found = repository.findById(request.getResref());
        if (found.isPresent())
            return false;
        SpawnMonster monster = new SpawnMonster();
        monster.setResref(request.getResref());
        monster.setName(request.getName());
        monster.setCr(request.getCr());
        monster.setBaseType(request.getBaseType().getType());
        monster.setSpecialLoot(request.getSpecialLoot());
        repository.save(monster);
        return true;
    }
}

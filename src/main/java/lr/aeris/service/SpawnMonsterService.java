package lr.aeris.service;

import lr.aeris.model.SpawnMonster;
import lr.aeris.model.SpawnPair;
import lr.aeris.model.SpawnRule;
import lr.aeris.model.SpawnType;
import lr.aeris.repositories.SpawnMonsterRepository;
import lr.aeris.requests.AddMonsterRequest;
import lr.aeris.requests.ChangeMonsterRequest;
import lr.aeris.requests.DeleteMonsterRequest;
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
        if(!request.getResref().trim().isEmpty()){
            result = repository.findByResrefContainingIgnoreCase(request.getResref());
        } else {
            result = repository.findAll();
        }

        List<String> resrefsPairedWithType = new ArrayList<>();
        if(!request.getBaseType().getType().trim().isEmpty())
        {
            resrefsPairedWithType = spawnPairService.getResrefsPairedWithType(request.getBaseType().getType());
        }
        List<String> finalResrefsPairedWithType = resrefsPairedWithType;

        List<SpawnMonster> monstersPairedWithArea = new ArrayList<>();
        if(!request.getAreaName().trim().isEmpty() || !request.getAreaTag().trim().isEmpty())
        {
            String areaName = "%" + request.getAreaName().trim() + "%";
            String areaTag = "%" + request.getAreaTag().trim() + "%";
            monstersPairedWithArea = repository.findByAreaNameAndTag(areaName, areaTag);
        }
        List<SpawnMonster> finalMonstersPairedWithArea = monstersPairedWithArea;

        result = result.stream()
                .filter(r -> r.getName().toLowerCase().contains(request.getName().toLowerCase()))
                .filter(r -> request.getCr() <= -1 || r.getCr().equals(request.getCr()))
                .filter(r -> request.getBaseType().getType().trim().isEmpty() || finalResrefsPairedWithType.contains(r.getResref()))
                .filter(r -> finalMonstersPairedWithArea.isEmpty() || finalMonstersPairedWithArea.contains(r))
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

    @Transactional
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

    @Transactional
    public boolean deleteMonster(DeleteMonsterRequest request) {
        Optional<SpawnMonster> found = repository.findById(request.getResref());
        if (found.isPresent()) {
            repository.delete(found.get());
            return true;
        } else {
            return false;
        }
    }
}

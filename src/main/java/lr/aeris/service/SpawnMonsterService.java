package lr.aeris.service;

import lr.aeris.model.SpawnMonster;
import lr.aeris.repositories.SpawnMonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpawnMonsterService {

    private final SpawnMonsterRepository repository;

    @Autowired
    public SpawnMonsterService(SpawnMonsterRepository repository){
        this.repository = repository;
    }

    public List<SpawnMonster> findAllMonsters(){
        return repository.findAll();
    }

    public List<String> getAllMonsterResrefs(){
        List<SpawnMonster> monsterList = findAllMonsters();
        List<String> result = new ArrayList<>();
        monsterList.forEach(m -> result.add(m.getResref()));
        return result;
    }
}

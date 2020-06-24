package lr.aeris.service;

import lr.aeris.model.SpawnMonster;
import lr.aeris.repositories.SpawnMonsterRepository;
import lr.aeris.requests.ChangeMonsterRequest;
import lr.aeris.requests.SpawnMonsterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<SpawnMonster> findMonstersMatchingQuery(SpawnMonsterRequest request){
        List<SpawnMonster> result;
        if(!request.getResref().isEmpty()){
            result = repository.findByResrefContainingIgnoreCase(request.getResref());
        } else {
            result = repository.findAll();
        }
        result = result.stream()
                .filter(r -> r.getName().contains(request.getName()))
                .filter(r -> request.getCr() <= -1 || r.getCr().equals(request.getCr()))
                .filter(r -> r.getBaseType().equals(request.getBaseType().getType()))
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
}

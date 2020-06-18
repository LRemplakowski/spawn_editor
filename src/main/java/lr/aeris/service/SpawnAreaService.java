package lr.aeris.service;

import lr.aeris.model.SpawnArea;
import lr.aeris.repositories.SpawnAreaRepository;
import lr.aeris.requests.ChangeAreaRequest;
import lr.aeris.requests.SpawnAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<String> getAllAreatags(){
        List<SpawnArea> areaList = findAllAreas();
        List<String> result = new ArrayList<>();
        areaList.forEach(a -> result.add(a.getAreatag()));
        return result;
    }

    public List<String> findByPartialAreatag(String areatag){
        List<SpawnArea> areaList = repository.findByAreatagContainingIgnoreCase(areatag);
        List<String> result = new ArrayList<>();
        areaList.forEach(a -> result.add(a.getAreatag()));
        return result;
    }

    public SpawnArea findByAreatag(String areatag){
        Optional<SpawnArea> a = repository.findById(areatag);
        return a.orElse(null);
    }

    public List<String> findByQuery(SpawnAreaRequest request) {
        List<SpawnArea> areaList;
        if(request.getAreatag().equals("")){
            areaList = repository.findAll();
        } else {
            areaList = repository.findByAreatagContainingIgnoreCase(request.getAreatag());
        }
        areaList = areaList.stream()
                .filter(a -> a.getCrmin() >= request.getCrMin())
                .filter(a -> a.getCrmax() <= request.getCrMax())
                .filter(a -> a.getMinmobs() >= request.getMinMobs())
                .filter(a -> a.getMaxmobs() <= request.getMaxMobs())
                .filter(a -> a.getHasspawn().equals(request.getHasSpawn()))
                .filter(a -> a.getCooldown() >= request.getCooldown())
                .collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        areaList.forEach(a -> result.add(a.getAreatag()));
        return result;
    }

    public void SaveEditedArea(ChangeAreaRequest request){
        Optional<SpawnArea> found = repository.findById(request.getAreatag());
        System.out.println("Looking for area");
        if(found.isPresent()){
            System.out.println("Area found, saving");
            SpawnArea area = found.get();
            area.setCrmin(request.getCrMin() != null ? request.getCrMin() : area.getCrmin());
            area.setCrmax(request.getCrMax() != null ? request.getCrMax() : area.getCrmax());
            area.setMinmobs(request.getMinMobs() != null ? request.getMinMobs() : area.getMinmobs());
            area.setMaxmobs(request.getMaxMobs() != null ? request.getMaxMobs() : area.getMaxmobs());
            area.setHasspawn(request.getHasSpawn() != null ? request.getHasSpawn() : area.getHasspawn());
            area.setCooldown(request.getCooldown() != null ? request.getCooldown() : area.getCooldown());
            repository.save(area);
        }
    }
}

package lr.aeris.service;

import lr.aeris.model.SpawnArea;
import lr.aeris.repositories.SpawnAreaRepository;
import lr.aeris.requests.ChangeAreaRequest;
import lr.aeris.requests.SpawnAreaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<SpawnArea> getAllAreas(){
        return repository.findAll();
    }

    public List<SpawnArea> findByQuery(SpawnAreaRequest request) {
        List<SpawnArea> areaList;
        if(request.getTag().equals("")){
            areaList = repository.findAll();
        } else {
            areaList = repository.findByTagContainingIgnoreCase(request.getTag());
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
        areaList.forEach(a -> result.add(a.getTag()));
        return areaList;
    }

    @Transactional
    public void saveEditedArea(ChangeAreaRequest request){
        Optional<SpawnArea> found = repository.findById(request.getTag());
        if(found.isPresent()){
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

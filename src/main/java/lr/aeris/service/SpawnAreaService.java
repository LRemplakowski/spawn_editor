package lr.aeris.service;

import lr.aeris.model.Area;
import lr.aeris.model.SpawnArea;
import lr.aeris.model.SpawnMonster;
import lr.aeris.repositories.SpawnAreaRepository;
import lr.aeris.requests.ChangeAreaRequest;
import lr.aeris.requests.SpawnAreaRequest;
import lr.aeris.requests.SpawnMonsterRequest;
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
    private final AreaService areaService;
    private final SpawnRuleService spawnRuleService;
    private final SpawnMonsterService spawnMonsterService;

    @Autowired
    public SpawnAreaService(SpawnAreaRepository repository, AreaService areaService,
                            SpawnRuleService spawnRuleService, SpawnMonsterService spawnMonsterService) {
        this.repository = repository;
        this.areaService = areaService;
        this.spawnRuleService = spawnRuleService;
        this.spawnMonsterService = spawnMonsterService;
    }

    public List<SpawnArea> getAllAreas(){
        return repository.findAll();
    }

    private List<SpawnArea> getSpawnAreasByName(String name) {
        List<Area> areas = areaService.findByPartialName(name);
        List<String> tags = new ArrayList<>();
        areas.forEach(a -> tags.add(a.getTag()));
        return repository.findAllById(tags);
    }

    public List<SpawnArea> findByQuery(SpawnAreaRequest request) {
        List<SpawnArea> areaList;
        if(!request.getName().trim().isEmpty()) {
            areaList = getSpawnAreasByName(request.getName());
            if(!request.getTag().trim().isEmpty()) {
                areaList = areaList.stream()
                        .filter(a -> a.getTag().contains(request.getTag()))
                        .collect(Collectors.toList());
            }
        } else if(request.getTag().trim().isEmpty()) {
            areaList = repository.findAll();
        } else {
            areaList = repository.findByTagContainingIgnoreCase(request.getTag());
        }
        if (!request.getMonsterType().trim().isEmpty()) {
            List<String> tagsContainingRule = spawnRuleService.findAreatagsByAssignedRule(request.getMonsterType());
            areaList = areaList.stream()
                    .filter(a -> tagsContainingRule.contains(a.getTag()))
                    .collect(Collectors.toList());
        }
        if (!request.getMonsterName().trim().isEmpty() || !request.getMonsterResref().trim().isEmpty()) {
            List<SpawnMonster> monsters = spawnMonsterService
                    .findMonstersMatchingQuery(
                            new SpawnMonsterRequest(request.getMonsterName(),
                                    request.getMonsterResref(),
                                    "",
                                    null,
                                    "",
                                    ""));
            List<SpawnArea> areasMatchingMonsterNameAndResref = repository.findSpawnAreasByMonsterNameAndResref(
                    request.getMonsterName().trim().isEmpty() ? "%" : "%"+request.getMonsterName()+"%",
                    request.getMonsterResref().trim().isEmpty() ? "%" : "%"+request.getMonsterResref()+"%"
            );
            areaList = areaList.stream()
                    .filter(a -> areasMatchingMonsterNameAndResref.stream().anyMatch(aa -> aa.getTag().equals(a.getTag())))
                    .collect(Collectors.toList());
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

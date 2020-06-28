package lr.aeris.service;

import lr.aeris.model.Area;
import lr.aeris.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    private final AreaRepository repository;

    @Autowired
    public AreaService(AreaRepository repository) {
        this.repository = repository;
    }

    public List<Area> findByPartialName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }

    public Area findByTag(String tag) {
        return repository.findByTag(tag).orElse(null);
    }
}

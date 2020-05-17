package lr.aeris.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_area_list")
@Data
@NoArgsConstructor
public class SpawnArea {
    @Id
    private String areatag;
    private Integer crMin;
    private Integer crMax;
    private Integer minMobs;
    private Integer maxMobs;
    private Integer hasSpawn;
    private Integer cooldown;
}

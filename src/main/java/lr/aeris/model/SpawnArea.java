package lr.aeris.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_area_list")
@Data
@NoArgsConstructor
@Getter
public class SpawnArea {
    @Id
    private String areatag;
    private Integer crMin;
    private Integer crMax;
    private Integer minMobs;
    private Integer maxMobs;
    private Integer hasSpawn;
    private Integer cooldown;

    public String getAreatag() {
        return areatag;
    }

    public Integer getCrMin() {
        return crMin;
    }

    public Integer getCrMax() {
        return crMax;
    }

    public Integer getMinMobs() {
        return minMobs;
    }

    public Integer getMaxMobs() {
        return maxMobs;
    }

    public Integer getHasSpawn() {
        return hasSpawn;
    }

    public Integer getCooldown() {
        return cooldown;
    }
}

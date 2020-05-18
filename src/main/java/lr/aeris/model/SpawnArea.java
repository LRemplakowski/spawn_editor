package lr.aeris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_area_list")
public class SpawnArea {
    @Id
    private String areatag;
    @Column(name = "crmin")
    private Integer crmin;
    @Column(name = "crmax")
    private Integer crmax;
    @Column(name = "minmobs")
    private Integer minmobs;
    @Column(name = "maxmobs")
    private Integer maxmobs;
    @Column(name = "hasspawn")
    private Integer hasspawn;
    private Integer cooldown;

    public String getAreatag() {
        return areatag;
    }

    public Integer getCrmin() {
        return crmin;
    }

    public Integer getCrmax() {
        return crmax;
    }

    public Integer getMinmobs() {
        return minmobs;
    }

    public Integer getMaxmobs() {
        return maxmobs;
    }

    public Integer getHasspawn() {
        return hasspawn;
    }

    public Integer getCooldown() {
        return cooldown;
    }
}

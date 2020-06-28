package lr.aeris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_area_list")
public class SpawnArea {
    @Id
    private String tag;
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

    public String getTag() {
        return tag;
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

    public void setCrmin(Integer crmin) {
        this.crmin = crmin;
    }

    public void setCrmax(Integer crmax) {
        this.crmax = crmax;
    }

    public void setMinmobs(Integer minmobs) {
        this.minmobs = minmobs;
    }

    public void setMaxmobs(Integer maxmobs) {
        this.maxmobs = maxmobs;
    }

    public void setHasspawn(Integer hasspawn) {
        this.hasspawn = hasspawn;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return tag;
    }
}

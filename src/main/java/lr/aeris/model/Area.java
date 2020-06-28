package lr.aeris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area_list")
public class Area {
    @Id
    private Integer id;
    private String tag;
    private Integer interior;
    private Integer valid;
    @Column(name = "has_snow")
    private Integer hasSnow;
    private Integer template;
    @Column(name = "start_location")
    private Integer startLocation;
    @Column(name = "save_location")
    private Integer saveLocation;
    @Column(name = "afterlife_location")
    private Integer afterlifeLocation;
    @Column(name = "deny_teleport")
    private Integer denyTeleport;
    @Column(name = "teleport_cost")
    private Integer teleportCost;
    @Column(name = "rest_type")
    private String rest_type;
    @Column(name = "full_name")
    private String fullName;

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public Integer getInterior() {
        return interior;
    }

    public Integer getValid() {
        return valid;
    }

    public Integer getHasSnow() {
        return hasSnow;
    }

    public Integer getTemplate() {
        return template;
    }

    public Integer getStartLocation() {
        return startLocation;
    }

    public Integer getSaveLocation() {
        return saveLocation;
    }

    public Integer getAfterlifeLocation() {
        return afterlifeLocation;
    }

    public Integer getDenyTeleport() {
        return denyTeleport;
    }

    public Integer getTeleportCost() {
        return teleportCost;
    }

    public String getRest_type() {
        return rest_type;
    }

    public String getFullName() {
        return fullName;
    }
}

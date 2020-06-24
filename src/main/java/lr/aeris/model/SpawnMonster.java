package lr.aeris.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_monsters")
@Data
@NoArgsConstructor
public class SpawnMonster {
    private String name;
    @Id
    private String resref;
    private Integer cr;
    private Integer special;
    @Column(name = "base_type")
    private String baseType;
    @Column(name = "special_loot")
    private String specialLoot;

    public String getName() {
        return name;
    }

    public String getResref() {
        return resref;
    }

    public Integer getCr() {
        return cr;
    }

    public Integer getSpecial() {
        return special;
    }

    public String getBaseType() {
        return baseType;
    }

    public String getSpecialLoot() {
        return specialLoot;
    }

    public void setCr(Integer cr) {
        this.cr = cr;
    }

    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    @Override
    public String toString() {
        return name+" - "+resref;
    }
}

package lr.aeris.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "spawn_monsters")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SpawnMonster {
    private String name;
    @Id
    private String resref;
    private Integer cr;
    @Column(name = "base_type")
    private String baseType;
    @Column(name = "special_loot")
    private String specialLoot;
    @Column(name = "spawn_chance")
    private Integer spawnChance;
    @Column(name = "exp_multiplier")
    private Float expMultiplier;

    @Override
    public String toString() {
        return name+" - "+resref;
    }
}

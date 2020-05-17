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
    private Integer baseType;
    @Column(name = "special_loot")
    private Integer specialLoot;
}

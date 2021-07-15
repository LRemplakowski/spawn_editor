package lr.aeris.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spawn_types")
@Data
@NoArgsConstructor
public class SpawnType implements Comparable<SpawnType>{
    @Id
    private String type = "";

    public SpawnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public int compareTo(SpawnType o) {
        return type.compareTo(o.getType());
    }
}

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
public class SpawnType {
    @Id
    private String type;

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}

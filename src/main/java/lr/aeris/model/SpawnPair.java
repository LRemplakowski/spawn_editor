package lr.aeris.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "spawn_pairs")
@IdClass(SpawnPair.class)
@NoArgsConstructor
@Data
public class SpawnPair implements Serializable {
    @Id
    private String resref;
    @Id
    private String type;

    public SpawnPair(String resref, String type) {
        this.resref = resref;
        this.type = type;
    }

    public String getResref() {
        return resref;
    }

    public String getType() {
        return type;
    }
}

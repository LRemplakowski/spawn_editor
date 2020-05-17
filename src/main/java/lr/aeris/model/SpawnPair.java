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
@Data
@NoArgsConstructor
@IdClass(SpawnPair.class)
public class SpawnPair implements Serializable {
    @Id
    private String resref;
    @Id
    private String type;
}

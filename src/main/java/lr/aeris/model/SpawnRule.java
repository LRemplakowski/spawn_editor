package lr.aeris.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "spawn_rules")
@Data
@NoArgsConstructor
@IdClass(SpawnRule.class)
public class SpawnRule implements Serializable {
    @Id
    private String rule;
    @Id
    private String areatag;

    public String getRule() {
        return rule;
    }

    public String getAreatag() {
        return areatag;
    }
}

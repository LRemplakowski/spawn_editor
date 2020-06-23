package lr.aeris.model;

import lombok.AllArgsConstructor;
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
public class SpawnRule implements Serializable, Comparable<String> {
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

    public SpawnRule(String rule, String areatag) {
        this.rule = rule;
        this.areatag = areatag;
    }

    @Override
    public String toString() {
        return rule;
    }

    @Override
    public int compareTo(String anotherString) {
        int len1 = rule.length();
        int len2 = anotherString.length();
        int lim = Math.min(len1, len2);
        char v1[] = rule.toCharArray();
        char v2[] = anotherString.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
}

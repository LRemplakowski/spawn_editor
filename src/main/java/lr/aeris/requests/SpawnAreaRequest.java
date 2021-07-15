package lr.aeris.requests;

import lombok.Getter;
import lr.aeris.model.SpawnType;

@Getter
public class SpawnAreaRequest {
    private final String tag;
    private final String name;
    private final Integer crMin;
    private final Integer crMax;
    private final Integer minMobs;
    private final Integer maxMobs;
    private final Integer hasSpawn;
    private final Integer cooldown;
    private final String monsterName;
    private final String monsterResref;
    private final String monsterType;

    @Override
    public String toString() {
        return "SpawnAreaRequest{" +
                "areatag='" + tag + '\'' +
                ", crMin=" + crMin +
                ", crMax=" + crMax +
                ", minMobs=" + minMobs +
                ", maxMobs=" + maxMobs +
                ", hasSpawn=" + hasSpawn +
                ", cooldown=" + cooldown +
                '}';
    }

    public SpawnAreaRequest(String tag, String name, String crMin, String crMax,
                            String minMobs, String maxMobs, Boolean hasSpawn,
                            String cooldown, String monsterName, String monsterResref,
                            String monsterType) {
        this.tag = tag;
        this.name = name;
        this.crMin = !crMin.equals("") ? Integer.parseInt(crMin) : Integer.MIN_VALUE;
        this.crMax = !crMax.equals("") ? Integer.parseInt(crMax) : Integer.MAX_VALUE;
        this.minMobs = !minMobs.equals("") ? Integer.parseInt(crMin) : Integer.MIN_VALUE;
        this.maxMobs = !maxMobs.equals("") ? Integer.parseInt(crMax) : Integer.MAX_VALUE;
        this.hasSpawn = hasSpawn ? 1 : 0;
        this.cooldown = !cooldown.equals("") ? Integer.parseInt(cooldown) : Integer.MIN_VALUE;
        this.monsterName = monsterName;
        this.monsterResref = monsterResref;
        this.monsterType = monsterType;
    }
}

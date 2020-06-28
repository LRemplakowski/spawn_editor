package lr.aeris.requests;

public class SpawnAreaRequest {
    private final String tag;
    private final String name;
    private final Integer crMin;
    private final Integer crMax;
    private final Integer minMobs;
    private final Integer maxMobs;
    private final Integer hasSpawn;
    private final Integer cooldown;

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

    public SpawnAreaRequest(String tag, String name, String crMin, String crMax, String minMobs, String maxMobs, Boolean hasSpawn, String cooldown) {
        this.tag = tag;
        this.name = name;
        this.crMin = !crMin.equals("") ? Integer.parseInt(crMin) : Integer.MIN_VALUE;
        this.crMax = !crMax.equals("") ? Integer.parseInt(crMax) : Integer.MAX_VALUE;
        this.minMobs = !minMobs.equals("") ? Integer.parseInt(crMin) : Integer.MIN_VALUE;
        this.maxMobs = !maxMobs.equals("") ? Integer.parseInt(crMax) : Integer.MAX_VALUE;
        this.hasSpawn = hasSpawn ? 1 : 0;
        this.cooldown = !cooldown.equals("") ? Integer.parseInt(cooldown) : Integer.MIN_VALUE;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public Integer getCrMin() {
        return crMin;
    }

    public Integer getCrMax() {
        return crMax;
    }

    public Integer getMinMobs() {
        return minMobs;
    }

    public Integer getMaxMobs() {
        return maxMobs;
    }

    public Integer getHasSpawn() {
        return hasSpawn;
    }

    public Integer getCooldown() {
        return cooldown;
    }

}

package lr.aeris.requests;

public class SpawnAreaRequest {
    private String areatag;
    private Integer crMin;
    private Integer crMax;
    private Integer minMobs;
    private Integer maxMobs;
    private Integer hasSpawn;
    private Integer cooldown;

    @Override
    public String toString() {
        return "SpawnAreaRequest{" +
                "areatag='" + areatag + '\'' +
                ", crMin=" + crMin +
                ", crMax=" + crMax +
                ", minMobs=" + minMobs +
                ", maxMobs=" + maxMobs +
                ", hasSpawn=" + hasSpawn +
                ", cooldown=" + cooldown +
                '}';
    }

    public SpawnAreaRequest(String areatag, String crMin, String crMax, String minMobs, String maxMobs, Boolean hasSpawn, String cooldown) {
        this.areatag = areatag;
//        System.out.println("MinCr inside contructor: "+crMin);
//        System.out.println("Integer.getInteger returns "+Integer.valueOf(crMin));
        this.crMin = !crMin.equals("") ? Integer.valueOf(crMin) : Integer.MIN_VALUE;
        this.crMax = !crMax.equals("") ? Integer.valueOf(crMax) : Integer.MAX_VALUE;
        this.minMobs = !minMobs.equals("") ? Integer.valueOf(crMin) : Integer.MIN_VALUE;
        this.maxMobs = !maxMobs.equals("") ? Integer.valueOf(crMax) : Integer.MAX_VALUE;
        this.hasSpawn = hasSpawn ? 1 : 0;
        this.cooldown = !cooldown.equals("") ? Integer.valueOf(cooldown) : Integer.MIN_VALUE;
    }

    public String getAreatag() {
        return areatag;
    }

    public void setAreatag(String areatag) {
        this.areatag = areatag;
    }

    public Integer getCrMin() {
        return crMin;
    }

    public void setCrMin(Integer crMin) {
        this.crMin = crMin;
    }

    public Integer getCrMax() {
        return crMax;
    }

    public void setCrMax(Integer crMax) {
        this.crMax = crMax;
    }

    public Integer getMinMobs() {
        return minMobs;
    }

    public void setMinMobs(Integer minMobs) {
        this.minMobs = minMobs;
    }

    public Integer getMaxMobs() {
        return maxMobs;
    }

    public void setMaxMobs(Integer maxMobs) {
        this.maxMobs = maxMobs;
    }

    public Integer getHasSpawn() {
        return hasSpawn;
    }

    public void setHasSpawn(Integer hasSpawn) {
        this.hasSpawn = hasSpawn;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
}

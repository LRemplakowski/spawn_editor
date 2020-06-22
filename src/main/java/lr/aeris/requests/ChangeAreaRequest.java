package lr.aeris.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ChangeAreaRequest {
    private String areatag;
    private Integer crMin;
    private Integer crMax;
    private Integer minMobs;
    private Integer maxMobs;
    private Integer hasSpawn;
    private Integer cooldown;
    private List<String> spawnRules;

    private ChangeAreaRequest(ChangeAreaRequestBuilder builder) {
        this.areatag = builder.areatag;
        this.crMin = builder.crMin;
        this.crMax = builder.crMax;
        this.minMobs = builder.minMobs;
        this.maxMobs = builder.maxMobs;
        this.hasSpawn = builder.hasSpawn;
        this.cooldown = builder.cooldown;
        this.spawnRules = builder.spawnRules;
    }

    public String getAreatag() {
        return areatag;
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

    public List<String> getSpawnRules() {
        return spawnRules;
    }

    public static class ChangeAreaRequestBuilder {

        //required
        private final String areatag;

        //optional
        private Integer crMin;
        private Integer crMax;
        private Integer minMobs;
        private Integer maxMobs;
        private Integer hasSpawn;
        private Integer cooldown;
        private List<String> spawnRules;

        public ChangeAreaRequestBuilder(String areatag){
            this.areatag = areatag;
        }

        public ChangeAreaRequestBuilder setCrMin(Integer crMin){
            this.crMin = crMin;
            return this;
        }

        public ChangeAreaRequestBuilder setCrMax(Integer crMax){
            this.crMax = crMax;
            return this;
        }

        public ChangeAreaRequestBuilder setMinMobs(Integer minMobs){
            this.minMobs = minMobs;
            return this;
        }

        public ChangeAreaRequestBuilder setMaxMobs(Integer maxMobs){
            this.maxMobs = maxMobs;
            return this;
        }

        public ChangeAreaRequestBuilder setHasSpawn(boolean hasSpawn){
            this.hasSpawn = hasSpawn ? 1 : 0;
            return this;
        }

        public ChangeAreaRequestBuilder setCooldown(Integer cooldown){
            this.cooldown = cooldown;
            return this;
        }

        public ChangeAreaRequestBuilder setSpawnRules(List<String> spawnRules){
            this.spawnRules = spawnRules != null ? spawnRules : new ArrayList<>();
            return this;
        }

        public ChangeAreaRequest build(){
            return new ChangeAreaRequest(this);
        }
    }
}

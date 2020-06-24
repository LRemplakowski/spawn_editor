package lr.aeris.requests;

import lr.aeris.model.SpawnType;

import java.util.List;

public class ChangeMonsterRequest {
    private String name;
    private String resref;
    private Integer cr;
    private String baseType;
    private List<String> spawnPairs;

    private ChangeMonsterRequest(ChangeMonsterRequestBuilder builder) {
        this.name = builder.name;
        this.resref = builder.resref;
        this.cr = builder.cr;
        this.baseType = builder.baseType;
        this.spawnPairs = builder.spawnPairs;
    }

    public String getName() {
        return name;
    }

    public String getResref() {
        return resref;
    }

    public Integer getCr() {
        return cr;
    }

    public String getBaseType() {
        return baseType;
    }

    public List<String> getSpawnPairs() {
        return spawnPairs;
    }

    public static class ChangeMonsterRequestBuilder {

        //required
        private final String resref;

        //optional
        private String name;
        private Integer cr;
        private String baseType;
        private List<String> spawnPairs;

        public ChangeMonsterRequestBuilder(String resref) {
            this.resref = resref;
        }

        public ChangeMonsterRequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ChangeMonsterRequestBuilder setCr(Integer cr) {
            this.cr = cr;
            return this;
        }

        public ChangeMonsterRequestBuilder setBaseType(SpawnType baseType) {
            this.baseType = baseType.getType();
            System.out.println("Debug baseType in builder: "+baseType);
            return this;
        }

        public ChangeMonsterRequestBuilder setSpawnPairs(List<String> spawnPairs) {
            this.spawnPairs = spawnPairs;
            return this;
        }

        public ChangeMonsterRequest build() {
            return new ChangeMonsterRequest(this);
        }
    }
}

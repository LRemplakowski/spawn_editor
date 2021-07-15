package lr.aeris.requests;

import com.sun.istack.NotNull;
import lombok.Getter;
import lr.aeris.model.SpawnType;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
public class AddMonsterRequest {

    private String name;
    private String resref;
    private Integer cr;
    private SpawnType baseType;
    private String specialLoot;

    private AddMonsterRequest(AddMonsterRequestBuilder builder) {
        this.name = builder.name;
        this.resref = builder.resref;
        this.cr = builder.cr;
        this.baseType = builder.baseType;
        this.specialLoot = builder.specialLoot;
    }

    public static class AddMonsterRequestBuilder {
        private String name;
        private String resref;
        private Integer cr;
        private SpawnType baseType;
        private String specialLoot;

        public AddMonsterRequestBuilder(@NotNull String resref) {
            this.resref = resref;
            this.name = resref;
            this.cr = 0;
            this.baseType = new SpawnType();
            baseType.setType("animal");
            this.specialLoot = "";
        }

        public AddMonsterRequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AddMonsterRequestBuilder setCr(int cr) {
            this.cr = cr;
            return this;
        }

        public AddMonsterRequestBuilder setBaseType(SpawnType baseType) {
            this.baseType = baseType;
            return this;
        }

        public AddMonsterRequestBuilder setSpecialLoot(String specialLoot) {
            this.specialLoot = specialLoot;
            return this;
        }

        public AddMonsterRequest build() {
            return new AddMonsterRequest(this);
        }
    }
}

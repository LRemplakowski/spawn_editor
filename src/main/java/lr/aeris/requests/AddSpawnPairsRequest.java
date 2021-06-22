package lr.aeris.requests;

import com.sun.istack.NotNull;
import lombok.Getter;
import lr.aeris.model.SpawnPair;
import lr.aeris.model.SpawnType;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddSpawnPairsRequest {

    private final List<SpawnType> spawnTypes;
    private final String resref;

    private AddSpawnPairsRequest(AddSpawnPairsRequestBuilder builder) {
        this.spawnTypes = builder.spawnTypes;
        this.resref = builder.resref;
    }

    public static class AddSpawnPairsRequestBuilder {
        private final List<SpawnType> spawnTypes;
        private final String resref;

        public AddSpawnPairsRequestBuilder(@NotNull List<SpawnType> spawnTypes, @NotNull String resref) {
            this.spawnTypes = spawnTypes;
            this.resref = resref;
        }

        public AddSpawnPairsRequest build() {
            return new AddSpawnPairsRequest(this);
        }
    }
}

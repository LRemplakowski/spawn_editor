package lr.aeris.requests;

public class SpawnTypeRequest {
    private String typeName;

    public SpawnTypeRequest(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}

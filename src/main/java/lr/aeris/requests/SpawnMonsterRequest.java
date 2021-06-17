package lr.aeris.requests;

import lr.aeris.model.SpawnType;

public class SpawnMonsterRequest {
    private String resref;
    private String name;
    private Integer cr;
    private SpawnType baseType;

    public SpawnMonsterRequest(String resref, String name, String cr, SpawnType baseType) {
        this.resref = resref;
        this.name = name;
        this.cr = !cr.equals("") ? Integer.parseInt(cr) : -1;
        this.baseType = baseType != null ? baseType : new SpawnType();
    }

    public String getResref() {
        return resref;
    }

    public String getName() {
        return name;
    }

    public Integer getCr() {
        return cr;
    }

    public SpawnType getBaseType() {
        return baseType;
    }
}

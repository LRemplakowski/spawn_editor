package lr.aeris.requests;

import lombok.Getter;
import lr.aeris.model.SpawnType;

@Getter
public class SpawnMonsterRequest {
    private String resref;
    private String name;
    private Integer cr;
    private SpawnType baseType;
    private String areaName;
    private String areaTag;

    public SpawnMonsterRequest(String resref, String name, String cr, SpawnType baseType, String areaName, String areaTag) {
        this.resref = resref;
        this.name = name;
        this.cr = !cr.equals("") ? Integer.parseInt(cr) : -1;
        this.baseType = baseType != null ? baseType : new SpawnType();
        this.areaName = areaName;
        this.areaTag = areaTag;
    }
}

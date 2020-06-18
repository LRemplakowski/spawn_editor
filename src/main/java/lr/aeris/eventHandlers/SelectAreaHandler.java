package lr.aeris.eventHandlers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lr.aeris.controler.RootControler;
import lr.aeris.model.SpawnArea;
import lr.aeris.service.SpawnAreaService;
import lr.aeris.service.SpawnRuleService;
import lr.aeris.service.SpawnTypeService;

import java.util.List;

public class SelectAreaHandler implements EventHandler<MouseEvent> {
    private final RootControler rootControler;
    private final SpawnAreaService areaService;
    private final SpawnRuleService ruleService;
    private final SpawnTypeService typeService;

    public SelectAreaHandler(RootControler rootControler, SpawnAreaService areaService, SpawnRuleService ruleService, SpawnTypeService typeService) {
        this.rootControler = rootControler;
        this.areaService = areaService;
        this.ruleService = ruleService;
        this.typeService = typeService;
    }

    @Override
    public void handle(MouseEvent event) {
        String areatag;
        try {
            areatag = rootControler.getAreaList().getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            areatag = "";
        }
        rootControler.getSelectedAreatag().setText(areatag);
        SpawnArea area;
        if(!areatag.equals("")){
            area = areaService.findByAreatag(areatag);
        } else {
            return;
        }
        rootControler.getSelectedCrMin().setText(area.getCrmin().toString());
        rootControler.getSelectedCrMax().setText(area.getCrmax().toString());
        rootControler.getSelectedMinMobs().setText(area.getMinmobs().toString());
        rootControler.getSelectedMaxMobs().setText(area.getMaxmobs().toString());
        rootControler.getSelectedHasSpawn().setSelected(area.getHasspawn()==1);
        rootControler.getSelectedCooldown().setText(area.getCooldown().toString());
        List<String> areaRules = ruleService.findRulesByAreatag(areatag);
        rootControler.getSelectedAreaRuleList().setItems(FXCollections.observableList(areaRules));
        List<String> otherRules = typeService.findTypesOtherThan(areaRules);
        rootControler.getSelectedRuleList().setItems(FXCollections.observableList(otherRules));
        rootControler.setSelectedAreaFieldsDisabled(false);
    }
}

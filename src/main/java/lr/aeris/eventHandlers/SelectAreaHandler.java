package lr.aeris.eventHandlers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lr.aeris.controller.AreaPageController;
import lr.aeris.model.SpawnArea;
import lr.aeris.service.SpawnRuleService;
import lr.aeris.service.SpawnTypeService;

import java.util.List;

public class SelectAreaHandler implements EventHandler<MouseEvent> {
    private final AreaPageController areaPageController;
    private final SpawnRuleService ruleService;
    private final SpawnTypeService typeService;

    public SelectAreaHandler(AreaPageController areaPageController, SpawnRuleService ruleService, SpawnTypeService typeService) {
        this.areaPageController = areaPageController;
        this.ruleService = ruleService;
        this.typeService = typeService;
    }

    @Override
    public void handle(MouseEvent event) {
        SpawnArea area;
        try {
            area = areaPageController.getAreaList().getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            area = new SpawnArea();
        }
        areaPageController.getSelectedTag().setText(area.getTag());
        if(area.getTag().equals("")){
            return;
        }
        areaPageController.getSelectedCrMin().setText(area.getCrmin().toString());
        areaPageController.getSelectedCrMax().setText(area.getCrmax().toString());
        areaPageController.getSelectedMinMobs().setText(area.getMinmobs().toString());
        areaPageController.getSelectedMaxMobs().setText(area.getMaxmobs().toString());
        areaPageController.getSelectedHasSpawn().setSelected(area.getHasspawn()==1);
        areaPageController.getSelectedCooldown().setText(area.getCooldown().toString());
        List<String> areaRules = ruleService.findRulesByAreatag(area.toString());
        areaPageController.getSelectedAreaRuleList().setItems(FXCollections.observableList(areaRules));
        List<String> otherRules = typeService.findTypeNamesOtherThan(areaRules);
        areaPageController.getSelectedRuleList().setItems(FXCollections.observableList(otherRules));
        areaPageController.setSelectedAreaFieldsDisabled(false);
        //areaPageController.getAreaList().setDisable(true);
    }
}

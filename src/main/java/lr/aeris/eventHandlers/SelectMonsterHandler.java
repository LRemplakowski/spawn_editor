package lr.aeris.eventHandlers;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lr.aeris.controller.MonsterPageController;
import lr.aeris.model.SpawnMonster;
import lr.aeris.model.SpawnType;
import lr.aeris.service.SpawnMonsterService;
import lr.aeris.service.SpawnPairService;
import lr.aeris.service.SpawnTypeService;

import java.util.List;

public class SelectMonsterHandler implements EventHandler<MouseEvent> {
    private final MonsterPageController pageController;
    private final SpawnMonsterService monsterService;
    private final SpawnTypeService typeService;
    private final SpawnPairService pairService;

    public SelectMonsterHandler(MonsterPageController pageController, SpawnMonsterService monsterService, SpawnTypeService typeService, SpawnPairService pairService) {
        this.pageController = pageController;
        this.monsterService = monsterService;
        this.typeService = typeService;
        this.pairService = pairService;
    }

    @Override
    public void handle(MouseEvent event) {
        SpawnMonster monster;
        try {
            monster = pageController.getSpawnMonsterList().getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            monster = new SpawnMonster();
        }
        if(monster.getResref().equals("")){
            return;
        }
        pageController.getSelectedResref().setText(monster.getResref());
        pageController.getSelectedName().setText(monster.getName());
        pageController.getSelectedCr().setText(monster.getCr().toString());
        SpawnType selectedMonsterType = typeService.findTypeById(monster.getBaseType());
        pageController.getSelectedBaseType().getSelectionModel().select(selectedMonsterType);
        List<String> selectedSpawnTypeList = pairService.getPairTypesByResref(monster.getResref());
        pageController.getSelectedMonsterTypesList().setItems(FXCollections.observableList(selectedSpawnTypeList));
        List<String> otherTypeList = typeService.findTypeNamesOtherThan(selectedSpawnTypeList, selectedMonsterType.getType());
        pageController.getSelectedOtherTypesList().setItems(FXCollections.observableList(otherTypeList));
        pageController.setSelectedMonsterFieldsDisabled(false);
        //pageController.getSpawnMonsterList().setDisable(true);
    }
}

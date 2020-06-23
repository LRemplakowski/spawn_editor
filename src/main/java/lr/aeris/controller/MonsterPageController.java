package lr.aeris.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lr.aeris.model.SpawnMonster;
import lr.aeris.model.SpawnType;
import lr.aeris.service.SpawnMonsterService;
import lr.aeris.service.SpawnPairService;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("monsterPage.fxml")
public class MonsterPageController {
    private final SpawnMonsterService monsterService;
    private final SpawnTypeService typeService;
    private final SpawnPairService pairService;

    @FXML
    private TextField queryMonsterName;
    @FXML
    private TextField queryMonsterResref;
    @FXML
    private TextField queryMonsterCr;
    @FXML
    private ListView<SpawnType> queryMonsterType;

    public MonsterPageController(SpawnMonsterService monsterService, SpawnTypeService typeService, SpawnPairService pairService) {
        this.monsterService = monsterService;
        this.typeService = typeService;
        this.pairService = pairService;
    }

    @FXML
    public void initialize(){
        queryMonsterType.setItems(FXCollections.observableList(typeService.findAllTypes()));
    }

}

package lr.aeris.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lr.aeris.model.SpawnArea;
import lr.aeris.service.SpawnAreaService;
import lr.aeris.service.SpawnMonsterService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("root.fxml")
public class RootController {

    private final SpawnAreaService areaService;
    private final SpawnMonsterService monsterService;

    @FXML
    private ListView<String> areaList;

    @FXML
    private ListView<String> monsterList;

    @FXML
    private Label label;

    public RootController(SpawnAreaService areaService, SpawnMonsterService monsterService) {
        this.areaService = areaService;
        this.monsterService = monsterService;
    }

    @FXML
    public void initialize(){
        ObservableList<String> observableList = FXCollections.observableList(areaService.getAllAreatags());
        areaList.setItems(observableList);
        observableList = FXCollections.observableList(monsterService.getAllMonsterResrefs());
        monsterList.setItems(observableList);
    }
}

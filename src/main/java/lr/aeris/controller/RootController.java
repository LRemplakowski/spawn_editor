package lr.aeris.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lr.aeris.model.SpawnArea;
import lr.aeris.requests.SpawnAreaRequest;
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
    private TextField queryAreatag;
    @FXML
    @FXML
    private TextField queryMinCr;
    @FXML
    private TextField queryMaxCr;
    @FXML
    private TextField queryMinMobs;
    @FXML
    private TextField queryMaxMobs;
    @FXML
    private CheckBox queryHasspawn;
    @FXML
    private TextField queryCooldown;

    @FXML
    private Button findAll;
    @FXML
    private Button findQuery;

    public RootController(SpawnAreaService areaService, SpawnMonsterService monsterService) {
        this.areaService = areaService;
        this.monsterService = monsterService;
    }

    @FXML
    public void initialize(){
//        ObservableList<String> observableList = FXCollections.observableList(areaService.getAllAreatags());
//        areaList.setItems(observableList);
//        observableList = FXCollections.observableList(monsterService.getAllMonsterResrefs());
//        monsterList.setItems(observableList);
        // force the field to be numeric only
        queryMinCr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryMinCr.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        queryMaxCr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryMaxCr.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        queryMinMobs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryMinMobs.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        queryMaxMobs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryMaxMobs.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        queryCooldown.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void findAllButton(){
        ObservableList<String> observableList = FXCollections.observableList(areaService.getAllAreatags());
        areaList.setItems(observableList);
    }

    @FXML
    public void findQueryButton(){
        //System.out.println("MinCr passed into constructor: "+queryMinCr.getText());
        SpawnAreaRequest request = new SpawnAreaRequest(
                queryAreatag.getText(),
                queryMinCr.getText(),
                queryMaxCr.getText(),
                queryMinMobs.getText(),
                queryMaxMobs.getText(),
                queryHasspawn.isSelected(),
                queryCooldown.getText());
        System.out.println(request.toString());
        ObservableList<String> observableList = FXCollections.observableList(areaService.findByQuery(request));
        areaList.setItems(observableList);
    }
}

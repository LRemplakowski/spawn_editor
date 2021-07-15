package lr.aeris.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import lr.aeris.App;
import lr.aeris.PopupStageInitializer;
import lr.aeris.model.SpawnType;
import lr.aeris.requests.AddMonsterRequest;
import lr.aeris.requests.AddSpawnPairsRequest;
import lr.aeris.service.SpawnMonsterService;
import lr.aeris.service.SpawnPairService;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("addMonster.fxml")
public class AddMonsterController {
    private final SpawnMonsterService monsterService;
    private final SpawnTypeService typeService;
    private final SpawnPairService pairService;

    @FXML
    private TextField resrefField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField crField;
    @FXML
    private ChoiceBox<SpawnType> baseTypeDropdown;
    @FXML
    private ListView<SpawnType> assignedTypes;
    @FXML
    private ListView<SpawnType> unassignedTypes;
    @FXML
    private Button typeAdd;
    @FXML
    private Button typeAddAll;
    @FXML
    private Button typeRemove;
    @FXML
    private Button typeRemoveAll;
    @FXML
    private Button buttonClose;
    @FXML
    private Button buttonSave;

    public AddMonsterController(SpawnMonsterService monsterService, SpawnTypeService typeService, SpawnPairService pairService) {
        this.monsterService = monsterService;
        this.typeService = typeService;
        this.pairService = pairService;
    }

    @FXML
    public void initialize() {
        List<SpawnType> spawnTypes = typeService.findAllTypes();
        unassignedTypes.setItems(FXCollections.observableList(spawnTypes));
        baseTypeDropdown.setItems(FXCollections.observableList(spawnTypes));
        baseTypeDropdown.getSelectionModel().select(0);
        assignedTypes.setItems(FXCollections.observableList(new ArrayList<SpawnType>()));

        crField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    crField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void onClose() {
        if (App.addMonsterStage != null) {
            App.addMonsterStage.close();
        }
    }

    @FXML
    public void addType() {
        SpawnType rule = unassignedTypes.getSelectionModel().getSelectedItem();
        if (rule != null && !rule.getType().equals("")){
            if(assignedTypes.getItems() != null){
                assignedTypes.getItems().add(rule);
            } else {
                List<SpawnType> newList = new ArrayList<>();
                newList.add(rule);
                assignedTypes.setItems(FXCollections.observableList(newList));
            }
            unassignedTypes.getItems().remove(rule);
            FXCollections.sort(assignedTypes.getItems());
        }
    }

    @FXML
    public void addAllTypes() {
        if(unassignedTypes.getItems() != null) {
            if (assignedTypes.getItems() != null) {
                assignedTypes.getItems().addAll(unassignedTypes.getItems());
            } else {
                assignedTypes.setItems(unassignedTypes.getItems());
            }
            unassignedTypes.getItems().clear();
            FXCollections.sort(assignedTypes.getItems());
        }
    }

    @FXML
    public void removeType() {
        SpawnType rule = assignedTypes.getSelectionModel().getSelectedItem();
        if(rule != null && !rule.getType().equals("")) {
            if (unassignedTypes.getItems() != null) {
                unassignedTypes.getItems().add(rule);
            } else {
                List<SpawnType> newList = new ArrayList<>();
                newList.add(rule);
                unassignedTypes.setItems(FXCollections.observableList(newList));
            }
            assignedTypes.getItems().remove(rule);
            FXCollections.sort(unassignedTypes.getItems());
        }
    }

    @FXML
    public void removeAllTypes() {
        if(assignedTypes.getItems() != null) {
            if (unassignedTypes.getItems() != null) {
                unassignedTypes.getItems().addAll(assignedTypes.getItems());
            } else {
                unassignedTypes.setItems(assignedTypes.getItems());
            }
            assignedTypes.getItems().clear();
            FXCollections.sort(unassignedTypes.getItems());
        }
    }

    @FXML
    public void addMonsterEntry() {
        System.out.println("Saving entry!");
        if (resrefField.getText().equals("")) {
            App.addMonsterErrorPopup = PopupStageInitializer.getInstance().initializePopup(SmallPopupController.class, App.addMonsterStage);
            return;
        }
        AddMonsterRequest addMonsterRequest = new AddMonsterRequest
                .AddMonsterRequestBuilder(resrefField.getText())
                .setBaseType(baseTypeDropdown.getSelectionModel().getSelectedItem())
                .setName(nameField.getText())
                .setCr(Integer.parseInt(crField.getText().equals("") ? "0" : crField.getText()))
                .build();
        AddSpawnPairsRequest spawnPairsRequest = new AddSpawnPairsRequest
                .AddSpawnPairsRequestBuilder(assignedTypes.getItems(), resrefField.getText())
                .build();
        boolean added = monsterService.addNewMonster(addMonsterRequest);
        if (added)
            added = pairService.addSpawnPairs(spawnPairsRequest);
        if (added) {
            System.out.println("Dodano potwora!");
            App.addMonsterStage.close();
        }
    }
}

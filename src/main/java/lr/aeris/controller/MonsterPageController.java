package lr.aeris.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lr.aeris.eventHandlers.SelectMonsterHandler;
import lr.aeris.model.SpawnMonster;
import lr.aeris.model.SpawnType;
import lr.aeris.requests.ChangeMonsterRequest;
import lr.aeris.requests.SpawnMonsterRequest;
import lr.aeris.service.SpawnMonsterService;
import lr.aeris.service.SpawnPairService;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @FXML
    private Button buttonFindQuery;
    @FXML
    private Button buttonFindAll;
    @FXML
    private ListView<SpawnMonster> spawnMonsterList;
    @FXML
    private TextField selectedResref;
    @FXML
    private TextField selectedName;
    @FXML
    private TextField selectedCr;
    @FXML
    private ChoiceBox<SpawnType> selectedBaseType;
    @FXML
    private ListView<String> selectedOtherTypesList;
    @FXML
    private ListView<String> selectedMonsterTypesList;
    @FXML
    private Button typeAdd;
    @FXML
    private Button typeAddAll;
    @FXML
    private Button typeRemove;
    @FXML
    private Button typeRemoveAll;
    @FXML
    private Button buttonDiscardChanges;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonClearSelection;

    public MonsterPageController(SpawnMonsterService monsterService, SpawnTypeService typeService, SpawnPairService pairService) {
        this.monsterService = monsterService;
        this.typeService = typeService;
        this.pairService = pairService;
    }

    @FXML
    public void initialize(){
        queryMonsterCr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryMonsterCr.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        selectedCr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    selectedCr.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        queryMonsterType.setItems(FXCollections.observableList(typeService.findAllTypes()));
        spawnMonsterList.setOnMouseClicked(new SelectMonsterHandler(this, monsterService, typeService, pairService));
        selectedBaseType.setItems(FXCollections.observableList(typeService.findAllTypes()));
    }

    @FXML
    public void clearBaseTypeSelection()
    {
        queryMonsterType.getSelectionModel().clearSelection();
    }

    @FXML
    public void listMonstersMatchingQuery(){
        SpawnMonsterRequest request = new SpawnMonsterRequest(queryMonsterResref.getText(), queryMonsterName.getText(), queryMonsterCr.getText(), queryMonsterType.getSelectionModel().getSelectedItem());
        spawnMonsterList.setItems(FXCollections.observableList(monsterService.findMonstersMatchingQuery(request)));
        clearMonsterSelection();
    }

    @FXML
    public void listAllMonsters(){
        spawnMonsterList.setItems(FXCollections.observableList(monsterService.findAllMonsters()));
        clearMonsterSelection();
    }

    @FXML
    public void addType() {
        String rule = selectedOtherTypesList.getSelectionModel().getSelectedItem();
        if (rule != null && !rule.equals("")){
            if(selectedMonsterTypesList.getItems() != null){
                selectedMonsterTypesList.getItems().add(rule);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(rule);
                selectedMonsterTypesList.setItems(FXCollections.observableList(newList));
            }
            selectedOtherTypesList.getItems().remove(rule);
            FXCollections.sort(selectedMonsterTypesList.getItems());
        }
    }

    @FXML
    public void addAllTypes() {
        if(selectedOtherTypesList.getItems() != null) {
            if (selectedMonsterTypesList.getItems() != null) {
                selectedMonsterTypesList.getItems().addAll(selectedOtherTypesList.getItems());
            } else {
                selectedMonsterTypesList.setItems(selectedOtherTypesList.getItems());
            }
            selectedOtherTypesList.getItems().clear();
            FXCollections.sort(selectedMonsterTypesList.getItems());
        }
    }

    @FXML
    public void removeType() {
        String rule = selectedMonsterTypesList.getSelectionModel().getSelectedItem();
        if(rule != null && !rule.equals("")) {
            if (selectedOtherTypesList.getItems() != null) {
                selectedOtherTypesList.getItems().add(rule);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(rule);
                selectedOtherTypesList.setItems(FXCollections.observableList(newList));
            }
            selectedMonsterTypesList.getItems().remove(rule);
            FXCollections.sort(selectedOtherTypesList.getItems());
        }
    }

    @FXML
    public void removeAllTypes() {
        if(selectedMonsterTypesList.getItems() != null) {
            if (selectedOtherTypesList.getItems() != null) {
                selectedOtherTypesList.getItems().addAll(selectedMonsterTypesList.getItems());
            } else {
                selectedOtherTypesList.setItems(selectedMonsterTypesList.getItems());
            }
            selectedMonsterTypesList.getItems().clear();
            FXCollections.sort(selectedOtherTypesList.getItems());
        }
    }

    @FXML
    public void saveChanges() {
        ChangeMonsterRequest request = new ChangeMonsterRequest.ChangeMonsterRequestBuilder(getSelectedResref().getText())
                .setName(getSelectedName().getText())
                .setCr(Integer.parseInt(selectedCr.getText()))
                .setBaseType(getSelectedBaseType().getSelectionModel().getSelectedItem())
                .setSpawnPairs(getSelectedMonsterTypesList().getItems())
                .build();
        monsterService.saveEditedMonster(request);
        pairService.saveEditedSpawnPairs(request);
        refreshMonsterEntity();
        clearMonsterSelection();
    }

    @FXML
    public void discardChanges() {
        clearMonsterSelection();
    }

    public ListView<SpawnMonster> getSpawnMonsterList() {
        return spawnMonsterList;
    }

    public TextField getSelectedResref() {
        return selectedResref;
    }

    public TextField getSelectedName() {
        return selectedName;
    }

    public TextField getSelectedCr() {
        return selectedCr;
    }

    public ChoiceBox<SpawnType> getSelectedBaseType() {
        return selectedBaseType;
    }

    public ListView<String> getSelectedOtherTypesList() {
        return selectedOtherTypesList;
    }

    public ListView<String> getSelectedMonsterTypesList() {
        return selectedMonsterTypesList;
    }

    public void setSelectedMonsterFieldsDisabled(boolean isDisabled) {
        selectedResref.setDisable(isDisabled);
        selectedName.setDisable(isDisabled);
        selectedCr.setDisable(isDisabled);
        selectedBaseType.setDisable(isDisabled);
        selectedMonsterTypesList.setDisable(isDisabled);
        selectedOtherTypesList.setDisable(isDisabled);
    }

    private void clearSelectedMonsterFields() {
        selectedResref.setText("");
        selectedName.setText("");
        selectedCr.setText("");
        selectedBaseType.getSelectionModel().clearSelection();
        selectedOtherTypesList.getItems().clear();
        selectedMonsterTypesList.getItems().clear();
    }

    private void clearMonsterSelection() {
        setSelectedMonsterFieldsDisabled(true);
        clearSelectedMonsterFields();
        spawnMonsterList.getSelectionModel().clearSelection();
        spawnMonsterList.setDisable(false);
    }

    private void refreshMonsterEntity() {
        SpawnMonster monster = getSpawnMonsterList().getSelectionModel().getSelectedItem();
        monster.setCr(Integer.parseInt(selectedCr.getText()));
        monster.setBaseType(selectedBaseType.getSelectionModel().getSelectedItem().getType());
    }
}

package lr.aeris.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lr.aeris.eventHandlers.SelectAreaHandler;
import lr.aeris.model.SpawnArea;
import lr.aeris.requests.ChangeAreaRequest;
import lr.aeris.requests.SpawnAreaRequest;
import lr.aeris.service.AreaService;
import lr.aeris.service.SpawnAreaService;
import lr.aeris.service.SpawnRuleService;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("areaPage.fxml")
public class AreaPageController {

    private final SpawnAreaService spawnAreaService;
    private final SpawnRuleService ruleService;
    private final SpawnTypeService typeService;
    private final AreaService areaService;

    @FXML
    private ListView<SpawnArea> areaList;
    @FXML
    private TextField queryTag;
    @FXML
    private TextField queryName;
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
    @FXML
    private TextField selectedTag;
    @FXML
    private TextField selectedName;
    @FXML
    private TextField selectedCrMin;
    @FXML
    private TextField selectedCrMax;
    @FXML
    private TextField selectedMinMobs;
    @FXML
    private TextField selectedMaxMobs;
    @FXML
    private CheckBox selectedHasSpawn;
    @FXML
    private TextField selectedCooldown;
    @FXML
    private ListView<String> selectedRuleList;
    @FXML
    private ListView<String> selectedAreaRuleList;
    @FXML
    private Button buttonAddRule;
    @FXML
    private Button buttonAddRuleAll;
    @FXML
    private Button buttonRemoveRule;
    @FXML
    private Button buttonRemoveRuleAll;
    @FXML
    private Button buttonDiscardChanges;
    @FXML
    private Button buttonSave;

    public AreaPageController(SpawnAreaService spawnAreaService, SpawnRuleService ruleService, SpawnTypeService typeService, AreaService areaService) {
        this.spawnAreaService = spawnAreaService;
        this.ruleService = ruleService;
        this.typeService = typeService;
        this.areaService = areaService;
    }

    @FXML
    public void initialize(){
        //Forces fields to take only proper input
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
        selectedCrMin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        selectedCrMax.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        selectedMinMobs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        selectedMaxMobs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        selectedCooldown.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    queryCooldown.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        areaList.setOnMouseClicked(new SelectAreaHandler(this, ruleService, typeService, areaService));
    }

    @FXML
    public void findAllButton(){
        ObservableList<SpawnArea> observableList = FXCollections.observableList(spawnAreaService.getAllAreas());
        areaList.setItems(observableList);
        clearAreaSelection();
    }

    @FXML
    public void findQueryButton(){
        //System.out.println("MinCr passed into constructor: "+queryMinCr.getText());
        SpawnAreaRequest request = new SpawnAreaRequest(
                queryTag.getText(),
                queryName.getText(),
                queryMinCr.getText(),
                queryMaxCr.getText(),
                queryMinMobs.getText(),
                queryMaxMobs.getText(),
                queryHasspawn.isSelected(),
                queryCooldown.getText());
        System.out.println(request.toString());
        ObservableList<SpawnArea> observableList = FXCollections.observableList(spawnAreaService.findByQuery(request));
        areaList.setItems(observableList);
        clearAreaSelection();
    }

    @FXML
    public void onSelectArea(MouseEvent click){
        setSelectedAreaFieldsDisabled(false);
    }

    @FXML
    public void onAddRule(){
        String rule = selectedRuleList.getSelectionModel().getSelectedItem();
        if (rule != null && !rule.equals("")){
            if(selectedAreaRuleList.getItems() != null){
                selectedAreaRuleList.getItems().add(rule);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(rule);
                selectedAreaRuleList.setItems(FXCollections.observableList(newList));
            }
            selectedRuleList.getItems().remove(rule);
            FXCollections.sort(selectedAreaRuleList.getItems());
        }
    }

    @FXML
    public void onAddRuleAll(){
        if(selectedRuleList.getItems() != null) {
            if (selectedAreaRuleList.getItems() != null) {
                selectedAreaRuleList.getItems().addAll(selectedRuleList.getItems());
            } else {
                selectedAreaRuleList.setItems(selectedRuleList.getItems());
            }
            selectedRuleList.getItems().clear();
            FXCollections.sort(selectedAreaRuleList.getItems());
        }
    }

    @FXML
    public void onRemoveRule(){
        String rule = selectedAreaRuleList.getSelectionModel().getSelectedItem();
        if(rule != null && !rule.equals("")) {
            if (selectedRuleList.getItems() != null) {
                selectedRuleList.getItems().add(rule);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(rule);
                selectedRuleList.setItems(FXCollections.observableList(newList));
            }
            selectedAreaRuleList.getItems().remove(rule);
            FXCollections.sort(selectedRuleList.getItems());
        }
    }

    @FXML
    public void onRemoveRuleAll(){
        if(selectedAreaRuleList.getItems() != null) {
            if (selectedRuleList.getItems() != null) {
                selectedRuleList.getItems().addAll(selectedAreaRuleList.getItems());
            } else {
                selectedRuleList.setItems(selectedAreaRuleList.getItems());
            }
            selectedAreaRuleList.getItems().clear();
            FXCollections.sort(selectedRuleList.getItems());
        }
    }

    @FXML
    public void onButtonSave(){
        ChangeAreaRequest request = new ChangeAreaRequest.ChangeAreaRequestBuilder(selectedTag.getText())
                .setCrMin(Integer.valueOf(getSelectedCrMin().getText()))
                .setCrMax(Integer.valueOf(getSelectedCrMax().getText()))
                .setMinMobs(Integer.valueOf(getSelectedMinMobs().getText()))
                .setMaxMobs(Integer.valueOf(getSelectedMaxMobs().getText()))
                .setHasSpawn(getSelectedHasSpawn().isSelected())
                .setCooldown(Integer.valueOf(getSelectedCooldown().getText()))
                .setSpawnRules(selectedAreaRuleList.getItems())
                .build();
        spawnAreaService.saveEditedArea(request);
        ruleService.saveEditedSpawnRules(request);
        clearAreaSelection();
    }

    @FXML
    public void onButtonReset(){
        clearAreaSelection();
    }

    public ListView<SpawnArea> getAreaList() {
        return areaList;
    }

    public TextField getSelectedTag() {
        return selectedTag;
    }

    public TextField getSelectedName() {
        return selectedName;
    }

    public TextField getSelectedCrMin() {
        return selectedCrMin;
    }

    public TextField getSelectedCrMax() {
        return selectedCrMax;
    }

    public TextField getSelectedMinMobs() {
        return selectedMinMobs;
    }

    public TextField getSelectedMaxMobs() {
        return selectedMaxMobs;
    }

    public CheckBox getSelectedHasSpawn() {
        return selectedHasSpawn;
    }

    public TextField getSelectedCooldown() {
        return selectedCooldown;
    }

    public ListView<String> getSelectedRuleList() {
        return selectedRuleList;
    }

    public ListView<String> getSelectedAreaRuleList() {
        return selectedAreaRuleList;
    }

    public void setSelectedAreaFieldsDisabled(boolean isDisabled){
        selectedCrMin.setDisable(isDisabled);
        selectedCrMax.setDisable(isDisabled);
        selectedMinMobs.setDisable(isDisabled);
        selectedMaxMobs.setDisable(isDisabled);
        selectedHasSpawn.setDisable(isDisabled);
        selectedCooldown.setDisable(isDisabled);
        selectedRuleList.setDisable(isDisabled);
        selectedAreaRuleList.setDisable(isDisabled);
    }

    public void clearSelectedAreaFields(){
        selectedTag.setText("");
        selectedName.setText("");
        selectedCrMin.setText("");
        selectedCrMax.setText("");
        selectedMinMobs.setText("");
        selectedMaxMobs.setText("");
        selectedHasSpawn.setSelected(false);
        selectedCooldown.setText("");
        selectedRuleList.getItems().clear();
        selectedAreaRuleList.getItems().clear();
    }

    public void clearAreaSelection(){
        setSelectedAreaFieldsDisabled(true);
        clearSelectedAreaFields();
        areaList.getSelectionModel().clearSelection();
        areaList.setDisable(false);
    }
}

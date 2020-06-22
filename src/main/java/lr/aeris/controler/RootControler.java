package lr.aeris.controler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lr.aeris.eventHandlers.SelectAreaHandler;
import lr.aeris.requests.ChangeAreaRequest;
import lr.aeris.requests.SpawnAreaRequest;
import lr.aeris.service.SpawnAreaService;
import lr.aeris.service.SpawnMonsterService;
import lr.aeris.service.SpawnRuleService;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@FxmlView("root.fxml")
public class RootControler {

    private final SpawnAreaService areaService;
    private final SpawnRuleService ruleService;
    private final SpawnTypeService typeService;
    private final SpawnMonsterService monsterService;

    @FXML
    private ListView<String> areaList;
    @FXML
    private ListView<String> monsterList;

    @FXML
    private TextField queryAreatag;
    //@FXML
    //private TextField queryAreaname;
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
    private TextField selectedAreatag;
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
    private Button buttonReject;
    @FXML
    private Button buttonSave;

    public RootControler(SpawnAreaService areaService, SpawnRuleService ruleService, SpawnTypeService typeService, SpawnMonsterService monsterService) {
        this.areaService = areaService;
        this.ruleService = ruleService;
        this.typeService = typeService;
        this.monsterService = monsterService;
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

        areaList.setOnMouseClicked(new SelectAreaHandler(this, areaService, ruleService, typeService));
    }

    @FXML
    public void findAllButton(){
        ObservableList<String> observableList = FXCollections.observableList(areaService.getAllAreatags());
        areaList.setItems(observableList);
        clearAreaSelection();
        areaList.setDisable(false);
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
        clearAreaSelection();
        areaList.setDisable(false);
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
            selectedRuleList.setItems(null);
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
            selectedAreaRuleList.setItems(null);
            FXCollections.sort(selectedRuleList.getItems());
        }
    }

    @FXML
    public void onButtonSave(){
        ChangeAreaRequest request = new ChangeAreaRequest.ChangeAreaRequestBuilder(selectedAreatag.getText())
                .setCrMin(Integer.valueOf(getSelectedCrMin().getText()))
                .setCrMax(Integer.valueOf(getSelectedCrMax().getText()))
                .setMinMobs(Integer.valueOf(getSelectedMinMobs().getText()))
                .setMaxMobs(Integer.valueOf(getSelectedMaxMobs().getText()))
                .setHasSpawn(getSelectedHasSpawn().isSelected())
                .setCooldown(Integer.valueOf(getSelectedCooldown().getText()))
                .setSpawnRules(selectedAreaRuleList.getItems())
                .build();
        areaService.saveEditedArea(request);
        ruleService.saveEditedSpawnRules(request);
    }

    @FXML
    public void onButtonReset(){
        clearAreaSelection();
        areaList.setDisable(false);
    }

    public ListView<String> getAreaList() {
        return areaList;
    }

    public TextField getSelectedAreatag() {
        return selectedAreatag;
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
        selectedAreatag.setText("");
        selectedCrMin.setText("");
        selectedCrMax.setText("");
        selectedMinMobs.setText("");
        selectedMaxMobs.setText("");
        selectedHasSpawn.setSelected(false);
        selectedCooldown.setText("");
        selectedRuleList.setItems(null);
        selectedAreaRuleList.setItems(null);
    }

    public void clearAreaSelection(){
        setSelectedAreaFieldsDisabled(true);
        clearSelectedAreaFields();
        areaList.getSelectionModel().clearSelection();
    }
}

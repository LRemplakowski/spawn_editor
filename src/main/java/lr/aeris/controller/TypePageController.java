package lr.aeris.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lr.aeris.controller.selectionModel.NoSelectionModel;
import lr.aeris.eventHandlers.AddTypeHandler;
import lr.aeris.eventHandlers.DeleteTypeHandler;
import lr.aeris.model.SpawnType;
import lr.aeris.requests.SpawnTypeRequest;
import lr.aeris.service.SpawnTypeService;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("typePage.fxml")
public class TypePageController {
    private SpawnTypeService typeService;

    @FXML
    private ListView<SpawnType> typeList;
    @FXML
    private TextField queryTypeName;
    @FXML
    private ImageView isUniqueIcon;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    public TypePageController(SpawnTypeService typeService) {
        this.typeService = typeService;
    }

    @FXML
    public void initialize() {
        saveButton.setOnAction(new AddTypeHandler(this, typeService));
        deleteButton.setOnAction(new DeleteTypeHandler(this, typeService));
        typeList.setSelectionModel(new NoSelectionModel<SpawnType>());
    }

    @FXML
    public void queryTypeNameModified() {
        String s = queryTypeName.getText();
        if(s.length() >= 1) {
            typeList.setItems(FXCollections.observableList(typeService.findTypesByQuery(new SpawnTypeRequest(queryTypeName.getText()))));
            boolean isUnique = isTypeUnique(s);
            setIsTypeUniqueIcon(isUnique);
            saveButton.setDisable(!isUnique);
            deleteButton.setDisable(isUnique);
        } else {
            typeList.getItems().clear();
            setIsTypeUniqueIcon(false);
            saveButton.setDisable(false);
            deleteButton.setDisable(false);
        }
    }

    public TextField getQueryTypeName() {
        return queryTypeName;
    }

    private boolean isTypeUnique(String typeName) {
        return typeService.findTypeById(typeName) == null;
    }

    private void setIsTypeUniqueIcon(boolean isUnique) {
        if(isUnique) {
            isUniqueIcon.setImage(new Image(TypePageController.class.getResourceAsStream("/lr/aeris/controller/unique.png")));
        } else {
            isUniqueIcon.setImage(new Image(TypePageController.class.getResourceAsStream("/lr/aeris/controller/notUnique.png")));
        }
    }
}

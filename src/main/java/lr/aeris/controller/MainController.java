package lr.aeris.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PopupControl;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lr.aeris.App;
import lr.aeris.PopupStageInitializer;
import lr.aeris.PrimaryStageInitializer;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@FxmlView("main.fxml")
public class MainController {

    private final FxWeaver fxWeaver;

    @Autowired
    public MainController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    private MenuItem addNewMonster;

    @FXML
    public void onAddNewMonster() {
        App.addMonsterStage = PopupStageInitializer.getInstance().initializePopup(AddMonsterController.class);
    }
}

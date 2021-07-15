package lr.aeris.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lr.aeris.App;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@FxmlView(value = "confirmationPopup.fxml")
public class ConfirmationPopupController {
    @FXML
    private Button buttonContinue;
    @FXML
    private Button buttonCancel;

    private final MonsterPageController monsterPageController;

    @Autowired
    public ConfirmationPopupController(MonsterPageController monsterPageController) {
        this.monsterPageController = monsterPageController;
    }

    @FXML
    public void cancel() {
        App.deleteMonsterConfirmationPopup.close();
    }

    @FXML
    public void proceed() {
        App.deleteMonsterConfirmationPopup.close();
        monsterPageController.requestDeleteMonsterEntry();
    }
}

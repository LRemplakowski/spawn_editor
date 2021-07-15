package lr.aeris.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import lr.aeris.App;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("smallPopup.fxml")
public class SmallPopupController {

    @FXML
    private Button closePopup;

    @FXML
    public void onClick() {
        App.addMonsterErrorPopup.close();
    }
}

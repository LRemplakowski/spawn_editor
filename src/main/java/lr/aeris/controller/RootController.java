package lr.aeris.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@FxmlView("root.fxml")
public class RootController {

    private final String greeting;

    @FXML
    private Label label;

    public RootController(@Value("dupa") String greeting) {
        this.greeting = greeting;
    }
}

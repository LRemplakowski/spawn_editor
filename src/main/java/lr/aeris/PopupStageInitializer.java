package lr.aeris;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lr.aeris.controller.AddMonsterController;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopupStageInitializer {

    private static PopupStageInitializer INSTANCE;

    private final FxWeaver fxWeaver;

    @Autowired
    private PopupStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        PopupStageInitializer.INSTANCE = this;
    }

    public static PopupStageInitializer getInstance() {
        return PopupStageInitializer.INSTANCE;
    }

    public <C> Stage initializePopup(Class<C> controllerClass) {
        Stage stage = new Stage();
        Scene scene = new Scene(fxWeaver.loadView(controllerClass));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.getPrimaryStage());
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public <C> Stage initializePopup(Class<C> controllerClass, Stage owner) {
        Stage stage = new Stage();
        Scene scene = new Scene(fxWeaver.loadView(controllerClass));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        return stage;
    }
}

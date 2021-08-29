package lr.aeris;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lr.aeris.controller.MainController;
import lr.aeris.events.StageReadyEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private static PrimaryStageInitializer INSTANCE;

    private final FxWeaver fxWeaver;

    @Autowired
    private PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        PrimaryStageInitializer.INSTANCE = this;
    }

    public static PrimaryStageInitializer getInstance() {
        return PrimaryStageInitializer.INSTANCE;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(fxWeaver.loadView(MainController.class));
        stage.setScene(scene);
        stage.setTitle("Spawn Editor v0.3.2");
        stage.getIcons().add(new Image(Objects.requireNonNull(PrimaryStageInitializer.class.getResourceAsStream("/icon.png"))));
        App.setPrimaryStageIfNull(stage);
        stage.show();
    }
}

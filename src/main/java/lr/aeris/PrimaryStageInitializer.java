package lr.aeris;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lr.aeris.controller.RootController;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;

    @Autowired
    public PrimaryStageInitializer(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.stage;
        Scene scene = new Scene(fxWeaver.loadView(RootController.class));
        stage.setScene(scene);
        stage.setTitle("Spawn Editor v0.1");
        stage.getIcons().add(new Image(PrimaryStageInitializer.class.getResourceAsStream("/icon.png")));
        stage.show();
    }
}

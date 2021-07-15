package lr.aeris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lr.aeris.events.StageReadyEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class App extends Application
{
    private ConfigurableApplicationContext context;

    private static Stage primaryStage;
    public static Stage addMonsterStage;
    public static Stage addMonsterErrorPopup;
    public static Stage deleteMonsterConfirmationPopup;

    public static void setPrimaryStageIfNull(Stage primaryStage)
    {
        if (primaryStage == null)
            App.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage()
    {
        return App.primaryStage;
    }

    @Override
    public void init() throws Exception {
        this.context = new SpringApplicationBuilder()
                .sources(App.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    public static void main( String[] args )
    {

        Application.launch(App.class, args);
    }
}

package lr.aeris;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception
    {
        Parent root =
                FXMLLoader.load(
                        getClass()
                                .getResource("/root.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

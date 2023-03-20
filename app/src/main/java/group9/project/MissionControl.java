package group9.project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Scene scene;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        createUI();

        createTimer();

        stage.setScene(scene);
        
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException
    {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MissionControl.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    private static void createUI()
    {
        
    }

    private static void createTimer()
    {
        AnimationTimer updateLoopController = new LoopController();

        updateLoopController.start();
    }
}
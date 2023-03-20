package group9.project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage mainStage;

    private static Scene mainScene;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        mainStage = stage;

        mainScene = new Scene(Renderer.getInstance().getView());


        createTimeline();


        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);
        
        mainStage.show();
    }

    private void createTimeline()
    {
        Timeline loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsEngine.STEP_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private void updateLoop()
    {
        PhysicsEngine.getInstance().update();

        Renderer.getInstance().update();
    }
}
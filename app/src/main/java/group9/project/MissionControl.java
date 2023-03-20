package group9.project;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage stage;

    private static Scene scene;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage newStage) throws IOException
    {
        stage = newStage;

        createUI();

        createTimer();
    }

    private static void createUI()
    {
        Pane testPane = createLabelPane();

        Scene scene = new Scene(testPane);

        stage.setScene(scene);
        
        stage.show();
    }

    private static Pane createLabelPane()
    {
        Pane pane  = new Pane();

        pane.getChildren().add(new Label("Hello Pane"));

        return pane;
    }

    private static Pane createVisualPane()
    {
        return null;
    }

    private static void createTimer()
    {
        Timeline loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsEngine.STEP_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private static void updateLoop()
    {
        PhysicsEngine.updatePhysicsObjects();
    }
}
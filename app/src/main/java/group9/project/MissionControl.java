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
    

    private Timeline loopTimeline;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        createPhysicsSystems();
        

        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());


        createTimeline();


        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);

        mainStage.setMaximized(true);
        
        mainStage.show();
    }

    private void createPhysicsSystems()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();

        PhysicsVisualizer.getInstance().start();
    }

    private void createTimeline()
    {
        loopTimeline = new Timeline(new KeyFrame(Duration.seconds(SimulationSettings.UNIVERSE_TICK_TIME), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private void updateLoop()
    {
        PhysicsVisualizer.getInstance().update();

        PhysicsEngine.getInstance().update();
    }
}
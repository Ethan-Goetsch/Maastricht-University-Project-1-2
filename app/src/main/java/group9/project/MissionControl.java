package group9.project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import group9.project.Events.EventManager;
import group9.project.Optimization.Optimization;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Managers.PhysicsVisualizer;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    //#region Singleton
    private static MissionControl instance;

    public static MissionControl getInstance()
    {
        if (instance == null)
        {
            instance = new MissionControl();
        }

        return instance;
    }
    //#endregion

    private static Stage mainStage;

    private static Scene mainScene;
    

    private Timeline loopTimeline;

    public MissionControl()
    {
        
    }

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        createSystems();

        
        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());



        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);

        mainStage.setMaximized(true);
        
        mainStage.show();


        createTimeline();
    }

    public void restart()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();
    }

    private void createSystems()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();

        PhysicsVisualizer.getInstance().start();

        EventManager.getInstance().start();
    }

    private void createTimeline()
    {
        loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsSettings.getUniverseTickTime()), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);

        loopTimeline.play();
    }

    private void updateLoop()
    {
        PhysicsEngine.getInstance().update();

        PhysicsVisualizer.getInstance().update();

        if (SimulationSettings.getDEVELOPMENT_MODE())
        {
            Optimization.getInstance().update();
        }
    }
}
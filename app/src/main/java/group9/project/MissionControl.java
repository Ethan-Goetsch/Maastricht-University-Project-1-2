package group9.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import group9.project.Managers.SystemsManager;
import group9.project.Managers.TimelineManager;
import group9.project.Optimization.OptimizationDevelopmentMode;
import group9.project.Physics.Managers.PhysicsVisualizer;
import group9.project.Settings.SimulationSettings;

/**
 * JavaFX App
 */
public class MissionControl extends Application
{
    private static Stage mainStage;

    private static Scene mainScene;

    public static void main(String[] args)
    {
        SystemsManager.getInstance().start();

        TimelineManager.getInstance().start();

        SimulationSettings.playSimulation();

        launch();
    }

    @Override
    public void start(Stage stage)
    {
        if (SimulationSettings.getOptimizationDevelopmentMode() != OptimizationDevelopmentMode.None)
        {
            return;
        }

        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());


        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);

        mainStage.setMaximized(true);
        
        
        mainStage.show();
    }
}
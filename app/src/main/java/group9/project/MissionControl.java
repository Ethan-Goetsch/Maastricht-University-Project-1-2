package group9.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import group9.project.Managers.SystemsManager;
import group9.project.Managers.TimelineManager;
import group9.project.Physics.Managers.PhysicsVisualizer;
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

    public MissionControl()
    {
        
    }

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        SystemsManager.getInstance().start();

        TimelineManager.getInstance().start();

        
        mainStage = stage;

        mainScene = new Scene(PhysicsVisualizer.getInstance().getView());


        mainStage.setTitle("Titanic Space Odyssey");

        mainStage.setScene(mainScene);

        mainStage.setMaximized(true);
        
        mainStage.show();
        

        SimulationSettings.playSimulation();
    }
}
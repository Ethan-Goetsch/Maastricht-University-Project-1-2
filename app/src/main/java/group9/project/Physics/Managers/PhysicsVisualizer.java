package group9.project.Physics.Managers;

import group9.project.Optimization.OptimizationDevelopmentMode;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.View.SimulationDetailView;
import group9.project.UI.View.SystemDetailView;
import group9.project.UI.View.SystemOrbitView;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Mathematics;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PhysicsVisualizer implements IStartable, IUpdateable
{
    //#region Singleton
    private static PhysicsVisualizer instance;

    public static PhysicsVisualizer getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsVisualizer();
        }

        return instance;
    }
    //#endregion
    
    private BorderPane view;


    private SimulationDetailView simulationView;

    private SystemDetailView systemView;

    private SystemOrbitView systemCanvas;


    private static final int WIDTH = 1800;

    private static final int HEIGHT = 1000;

    public Pane getView()
    {
        return view;
    }

    private static int getViewWidth()
    {
        return Mathematics.calculatePercentage(WIDTH, 100);
    }

    private static int getViewHeight()
    {
        return Mathematics.calculatePercentage(HEIGHT, 5);
    }

    public static int getCanvasWidth()
    {
        return Mathematics.calculatePercentage(WIDTH, 100);
    }

    public static int getCanvasHeight()
    {
        return Mathematics.calculatePercentage(HEIGHT, 90);
    }

    private PhysicsVisualizer()
    {
        
    }

    @Override
    public void start()
    {
        if (SimulationSettings.getOptimizationDevelopmentMode() != OptimizationDevelopmentMode.None)
        {
            return;
        }

        view = new BorderPane();

        view.setPrefSize(WIDTH, HEIGHT);


        simulationView = new SimulationDetailView(getViewWidth(), getViewHeight());

        systemView = new SystemDetailView(getViewWidth(), getViewHeight());

        systemCanvas = new SystemOrbitView(getCanvasWidth(), getCanvasHeight());


        view.setBottom(simulationView);

        view.setTop(systemView);

        view.setCenter(systemCanvas);
    }

    @Override
    public void update()
    {
        if (SimulationSettings.getOptimizationDevelopmentMode() != OptimizationDevelopmentMode.None)
        {
            return;
        }

        systemView.update();

        systemCanvas.update();
    }
}
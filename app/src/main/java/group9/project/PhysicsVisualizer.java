package group9.project;

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

    public static OrbitTrail orbitTrail = new OrbitTrail();

    private BorderPane view;


    private SimulationDetailView simulationView;

    private SystemDetailView systemView;

    private SystemOrbitView systemCanvas;


    private static final int WIDTH = 1800;

    private static final int HEIGHT = 1000;


    private static double xDragOffset;

    private static double yDragOffset;


    public Pane getView()
    {
        return view;
    }

    private static int getViewWidth()
    {
        return Mathematics.getPercentage(WIDTH, 100);
    }

    private static int getViewHeight()
    {
        return Mathematics.getPercentage(HEIGHT, 5);
    }

    public static int getCanvasWidth()
    {
        return Mathematics.getPercentage(WIDTH, 100);
    }

    public static int getCanvasHeight()
    {
        return Mathematics.getPercentage(HEIGHT, 90);
    }

    public static double getXDragOffset()
    {
        return xDragOffset;
    }

    public static double getYDragOffset()
    {
        return yDragOffset;
    }

    public static void setXDragOffset(double value)
    {
        xDragOffset = value;
    }

    public static void setYDragOffset(double value)
    {
        yDragOffset = value;
    }

    public PhysicsVisualizer()
    {
        instance = this;

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
    public void start()
    {
        
    }

    @Override
    public void update()
    {
        systemView.update();

        systemCanvas.update();
    }
}
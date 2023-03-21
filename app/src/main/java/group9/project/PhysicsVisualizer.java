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

    private BorderPane view;

    private SystemDetailView systemView;

    private SystemOrbitView systemCanvas;

    public static final int WIDTH = 800;

    public static final int HEIGHT = 600;

    public Pane getView()
    {
        return view;
    }

    public SystemDetailView getSystemView()
    {
        return systemView;
    }

    public SystemOrbitView getSystemCanvas()
    {
        return systemCanvas;
    }

    public PhysicsVisualizer()
    {
        view = new BorderPane();

        view.setPrefSize(WIDTH, HEIGHT);


        systemView = new SystemDetailView(WIDTH, HEIGHT, 100, 20);

        systemCanvas = new SystemOrbitView(WIDTH, HEIGHT, 100, 80);


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
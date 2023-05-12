package group9.project.UI.View;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.UI.Drawable.DrawableCelestialBodyUI;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableRocketShipUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SimulationDetailView extends PaneView
{
    private HBox paneBox;


    private Label simulationSpeedLabel;

    private Label simulationScaleLabel;


    private Slider simulationSpeedSlider;

    private Slider simulationScaleSlider;


    private Button pauseButton;

    public SimulationDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        initDrawables();

        start();
    }

    public void initDrawables()
    {
        PhysicsObject[] physicsObjects = PhysicsEngine.getInstance().getPhysicsObjects();
        
        DrawableManager dmanager = DrawableManager.getInstance();
                
        new DrawableCelestialBodyUI("sun", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Sun.getValue()], 10,10,"sun",Color.ORANGE);
        new DrawableCelestialBodyUI("mercury", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mercury.getValue()], 10, 10, "mercury", Color.GRAY);
        new DrawableCelestialBodyUI("venus", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Venus.getValue()], 10, 10, "venus", Color.YELLOW);
        new DrawableCelestialBodyUI("earth", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Earth.getValue()], 10, 10, "earth", Color.BLUE);
        new DrawableCelestialBodyUI("moon", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Moon.getValue()], 10, 10, "moon", Color.GRAY);
        new DrawableCelestialBodyUI("mars", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Mars.getValue()], 10, 10, "mars", Color.RED);
        new DrawableCelestialBodyUI("jupiter", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Jupiter.getValue()], 10, 10, "jupiter", Color.ORANGE);
        new DrawableCelestialBodyUI("saturn", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Saturn.getValue()], 10, 10, "saturn", Color.GRAY);
        new DrawableCelestialBodyUI("titan", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Titan.getValue()], 10, 10, "titan", Color.ORANGE);
        new DrawableCelestialBodyUI("uranus", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Uranus.getValue()], 10, 10, "uranus", Color.CYAN);
        new DrawableCelestialBodyUI("neptune", (CelestialBodyObject)physicsObjects[PhysicsObjectType.Neptune.getValue()], 10, 10, "neptune", Color.BLUE);
        new DrawableRocketShipUI("rocket", (RocketShipObject)physicsObjects[PhysicsObjectType.Rocket.getValue()], 10.0, 10.0, "rocket", Color.BLUE);
    }

    private String getSpeedText()
    {
        return "Simulation Speed : " + String.format("%.2f", SimulationSettings.getSimulationSpeed());
    }

    private String getScaleText()
    {
        return "Simulation Scale : " + String.format("%.2f", ScaleConverter.getScaleFactor());
    }

    private String getPauseButtonText()
    {
        return SimulationSettings.getIsSimulationPaused() ? "Resume Simulation" : "Pause Simulation";
    }

    protected void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 10, new Insets(15, 12, 15, 12));


        simulationSpeedLabel = GUI.createLabel(getSpeedText());

        simulationScaleLabel = GUI.createLabel(getScaleText());


        simulationSpeedLabel.setPrefWidth(135);

        simulationScaleLabel.setPrefWidth(135);


        simulationSpeedSlider = GUI.createSlider(SimulationSettings.getMinSimulationSpeed(), SimulationSettings.getMaxSimulationSpeed(), 1, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable, Number oldValue, Number newValue)
            {
                SimulationSettings.setSimulationSpeed(newValue.doubleValue());

                update();
            }
        });

        simulationScaleSlider = GUI.createSlider(SimulationSettings.getMinScaleFactor(), SimulationSettings.getMaxScaleFactor(), 0.5, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable, Number oldValue, Number newValue)
            {
                ScaleConverter.setScaleFactor(newValue.doubleValue());

                update();
            }
        });

        

        pauseButton = GUI.createButton("Pause Simulation", event -> onPauseButton());

        pauseButton.setPrefWidth(140);

        HBox.setMargin(pauseButton, new Insets(0, 0, 0, width / 1.55));
        

        paneBox.getChildren().add(simulationSpeedLabel);

        paneBox.getChildren().add(simulationSpeedSlider);


        paneBox.getChildren().add(simulationScaleLabel);

        paneBox.getChildren().add(simulationScaleSlider);

        paneBox.getChildren().add(pauseButton);


        getChildren().add(paneBox);
    }

    @Override
    public void update()
    {
        simulationSpeedLabel.setText(getSpeedText());

        simulationScaleLabel.setText(getScaleText());
    }

    private void onPauseButton()
    {
        SimulationSettings.playOrPauseSimulation();

        pauseButton.setText(getPauseButtonText());
    }
}
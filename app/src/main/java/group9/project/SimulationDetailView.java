package group9.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SimulationDetailView extends PaneView
{
    private HBox paneBox;

    private Slider simulationnSpeedSlider;

    private Slider simulationScaleSlider;

    public SimulationDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    @Override
    public void start()
    {
        GUI.setBackground(this, "silver");

        paneBox = GUI.createHBox(width, height, 50, new Insets(15, 12, 15, 12));

        simulationnSpeedSlider = GUI.createSlider(0.1, 10, 1, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
            {
                PhysicsEngine.setSimulationSpeed(newValue.doubleValue());
            }
        });

        simulationScaleSlider = GUI.createSlider(0.01, 5, 3, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
            {
                ScaleConverter.setScaleFactor(newValue.doubleValue());
            }
        });

        paneBox.getChildren().add(simulationnSpeedSlider);

        paneBox.getChildren().add(simulationScaleSlider);

        getChildren().add(paneBox);
    }

    @Override
    public void update()
    {

    }
}
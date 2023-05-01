package group9.project.UI.View;

import group9.project.Settings.SimulationSettings;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

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

        start();
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
                if (SimulationSettings.getMaxScaleFactor() - newValue.doubleValue() > SimulationSettings.getMinScaleFactor())
                {
                    ScaleConverter.setScaleFactor(SimulationSettings.getMaxScaleFactor() - newValue.doubleValue());
                }
                else
                {
                    ScaleConverter.setScaleFactor(SimulationSettings.getMinScaleFactor());
                }

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
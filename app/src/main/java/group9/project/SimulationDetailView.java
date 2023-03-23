package group9.project;

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

    private String getSpeedLabelText()
    {
        return "Simulation Speed : " + Math.round(SimulationSettings.getSimulationSpeed() * 100.0) / 100.0;
    }

    private String getScaleLabelText()
    {
        return "Simulation Scale : " + Math.round(ScaleConverter.getScaleFactor() * 100.0) / 100.0;
    }

    private String getPauseButtonText()
    {
        return SimulationSettings.getIsSimulationPaused() ? "Resume Simulation" : "Pause Simulation";
    }

    protected void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 10, new Insets(15, 12, 15, 12));


        simulationSpeedLabel = GUI.createLabel(getSpeedLabelText());

        simulationScaleLabel = GUI.createLabel(getScaleLabelText());


        simulationSpeedLabel.setPrefWidth(135);

        simulationScaleLabel.setPrefWidth(135);


        simulationSpeedSlider = GUI.createSlider(SimulationSettings.MIN_SIMULATION_SPEED, SimulationSettings.MAX_SIMULATION_SPEED, 1, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
            {
                SimulationSettings.setSimulationSpeed(newValue.doubleValue());

                update();
            }
        });

        simulationScaleSlider = GUI.createSlider(SimulationSettings.MIN_SCALE_FACTOR, SimulationSettings.MAX_SCALE_FACTOR, 0.5, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
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
        simulationSpeedLabel.setText(getSpeedLabelText());

        simulationScaleLabel.setText(getScaleLabelText());
    }

    private void onPauseButton()
    {
        SimulationSettings.playOrPauseSimulation();

        pauseButton.setText(getPauseButtonText());
    }
}
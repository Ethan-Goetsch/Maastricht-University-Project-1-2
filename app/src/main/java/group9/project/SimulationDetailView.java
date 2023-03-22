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
        return "Simulation Speed : " + Math.round(PhysicsEngine.getSimulationSpeed() * 100.0) / 100.0;
    }

    private String getScaleLabelText()
    {
        return "Simulation Scale : " + Math.round(ScaleConverter.getScaleFactor() * 100.0) / 100.0;
    }

    @Override
    public void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 25, new Insets(15, 12, 15, 12));


        simulationSpeedLabel = GUI.createLabel(getSpeedLabelText());

        simulationScaleLabel = GUI.createLabel(getScaleLabelText());


        simulationSpeedLabel.setPrefWidth(135);

        simulationScaleLabel.setPrefWidth(135);


        simulationSpeedSlider = GUI.createSlider(0.1, 10, 1, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
            {
                PhysicsEngine.setSimulationSpeed(newValue.doubleValue());

                update();
            }
        });

        simulationScaleSlider = GUI.createSlider(0.1, 3, 2, new ChangeListener<Number>()
        {
            public void changed(ObservableValue <? extends Number> observable,

            Number oldValue, Number newValue)
            {
                ScaleConverter.setScaleFactor(newValue.doubleValue());

                update();
            }
        });


        pauseButton = GUI.createButton("Pause Simulation", event -> onPauseButton());

        pauseButton.setPrefWidth(115);

        HBox.setMargin(pauseButton, new Insets(0, 0, 0, width / 1.6));
        

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
        PhysicsEngine.getInstance().playOrPauseSimulation();

        if (PhysicsEngine.getInstance().isPaused())
        {
            pauseButton.setText("Play Simulationn");
        }
        else
        {
            pauseButton.setText("Pause Simulationn");
        }
    }
}
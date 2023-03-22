package group9.project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SimulationDetailView extends PaneView
{
    private HBox paneBox;

    private Slider sliderSimulationSpeed;

    public SimulationDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    @Override
    public void start()
    {
        paneBox = createHBox(width, height, 50, new Insets(15, 12, 15, 12));

        sliderSimulationSpeed = createSlider(0.1, 10, 1);

        paneBox.getChildren().add(sliderSimulationSpeed);

        getChildren().add(paneBox);
    }

    private Slider createSlider(double minValue, double maxValue, double startingValue)
    {
        Slider newSlider = new Slider(minValue, maxValue, startingValue);

        newSlider.setBlockIncrement(0.1);

        newSlider.setShowTickLabels(true);

        newSlider.setShowTickMarks(true);

        newSlider.valueProperty().addListener
        (
            new ChangeListener<Number>()
            {
                public void changed(ObservableValue <? extends Number> observable,

                Number oldValue, Number newValue)
                {
                    PhysicsEngine.setSimulationSpeed(newValue.doubleValue());
                }
            }
        );

        return newSlider;
    }

    @Override
    public void update()
    {

    }
}
package group9.project;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class SimulationDetailView extends PaneView
{
    public SimulationDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    @Override
    public void start()
    {
        Slider sliderSimSpeed = new Slider(0,5,1);
        sliderSimSpeed.setBlockIncrement(0.1);
        sliderSimSpeed.setShowTickLabels(true);
        sliderSimSpeed.setShowTickMarks(true);

        sliderSimSpeed.valueProperty().addListener(
            new ChangeListener<Number>() {
                public void changed(ObservableValue <? extends Number> observable,
                Number oldValue, Number newValue)
                {
                    PhysicsEngine.setSimulationSpeed(newValue.doubleValue());
                }
            }
        );
        
        getChildren().add(sliderSimSpeed);
    }

    @Override
    public void update()
    {

    }
}
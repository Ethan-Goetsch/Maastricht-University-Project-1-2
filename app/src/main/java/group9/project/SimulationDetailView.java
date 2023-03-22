package group9.project;

import javafx.scene.control.Button;

public class SimulationDetailView extends PaneView
{
    public SimulationDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);
    }

    @Override
    public void start()
    {
        Button newButton = new Button("Teeeeest Button");
        
        getChildren().add(newButton);
    }

    @Override
    public void update()
    {

    }
}
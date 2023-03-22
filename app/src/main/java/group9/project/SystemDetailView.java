package group9.project;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SystemDetailView extends PaneView
{
    private DateView dateView;

    private HBox paneBox;

    
    private Label distanceLabel;

    private Label speedLabel;

    private Label dateLabel;

    public SystemDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        dateView = new DateView();

        start();
    }

    private String getDistanceText()
    {
        return "Distance To Titan : " + Math.round(PhysicsEngine.getSimulationSpeed() * 100.0) / 100.0;
    }

    private String getSpeedText()
    {
        return "Simulation Speed : " + Math.round(PhysicsEngine.getSimulationSpeed() * 100.0) / 100.0;
    }

    @Override
    public void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 50, new Insets(15, 12, 15, 12));


        distanceLabel = GUI.createLabel(getDistanceText());

        speedLabel = GUI.createLabel(getSpeedText());

        dateLabel = dateView.getLabel();


        distanceLabel.setPrefWidth(135);

        speedLabel.setPrefWidth(135);


        paneBox.getChildren().add(dateLabel);

        paneBox.getChildren().add(distanceLabel);

        paneBox.getChildren().add(speedLabel);

        
        getChildren().add(paneBox);
    }   

    @Override
    public void update()
    {
        dateView.update();

        distanceLabel.setText(getDistanceText());

        speedLabel.setText(getSpeedText());
    }
}
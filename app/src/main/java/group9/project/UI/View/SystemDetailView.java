package group9.project.UI.View;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.GUI;
import group9.project.Utility.Date.DateView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SystemDetailView extends PaneView
{
    private DateView dateView;

    private HBox paneBox;

    
    private Label dateLabel;

    private Label distanceLabel;

    private Label speedLabel;

    private Label thrusterForceLabel;

    private Label fuelConsumedLabel;

    public SystemDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        dateView = new DateView();

        start();
    }

    private String getDistanceText()
    {
        return "Distance To Titan : " + Math.round(PhysicsObjectData.getInstance().getRocketShipDistanceToTitan() * 100.0) / 100.0 + " " + "KM";
    }

    private String getSpeedText()
    {
        return "Rocket Ship Speed : " + Math.round(PhysicsObjectData.getInstance().getRocketShipSpeed() * 100.0) / 100.0 + " " + "KM/S";
    }

    private String getThrusterForceText()
    {
        return "Thruster Force : " + String.format("%.2f", PhysicsObjectData.getInstance().getRocketShipObject().getThrusterForce());
    }

    private String getFuelConsumedText()
    {
        return "Fuel Consumed : " + String.format("%.2f", PhysicsObjectData.getInstance().getRocketShipObject().getFuelConsumed());
    }

    protected void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 10, new Insets(15, 12, 15, 12));


        dateLabel = dateView.getLabel();

        distanceLabel = GUI.createLabel(getDistanceText());

        speedLabel = GUI.createLabel(getSpeedText());

        thrusterForceLabel = GUI.createLabel(getThrusterForceText());

        fuelConsumedLabel = GUI.createLabel(getFuelConsumedText());


        distanceLabel.setPrefWidth(215);

        speedLabel.setPrefWidth(180);


        paneBox.getChildren().add(dateLabel);

        paneBox.getChildren().add(distanceLabel);

        paneBox.getChildren().add(speedLabel);

        paneBox.getChildren().add(thrusterForceLabel);

        paneBox.getChildren().add(fuelConsumedLabel);

        
        getChildren().add(paneBox);
    }   

    @Override
    public void update()
    {
        dateView.update();

        distanceLabel.setText(getDistanceText());

        speedLabel.setText(getSpeedText());

        thrusterForceLabel.setText(getThrusterForceText());

        fuelConsumedLabel.setText(getFuelConsumedText());
    }
}
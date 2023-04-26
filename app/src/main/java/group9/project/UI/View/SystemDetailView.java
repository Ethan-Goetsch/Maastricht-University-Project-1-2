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
        return "Distance To Titan : " + Math.round(PhysicsObjectData.getInstance().getRocketShipDistanceToTitan() * 100.0) / 100.0 + " " + "KM";
    }

    private String getSpeedText()
    {
        return "Rocket Ship Speed : " + Math.round(PhysicsObjectData.getInstance().getRocketShipSpeed() * 100.0) / 100.0 + " " + "KM/S";
    }

    protected void start()
    {
        GUI.setBackground(this, "silver");


        paneBox = GUI.createHBox(width, height, 10, new Insets(15, 12, 15, 12));


        distanceLabel = GUI.createLabel(getDistanceText());

        speedLabel = GUI.createLabel(getSpeedText());

        dateLabel = dateView.getLabel();


        distanceLabel.setPrefWidth(215);

        speedLabel.setPrefWidth(215);


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
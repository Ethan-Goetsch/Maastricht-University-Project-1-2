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


    private String distanceText;

    private String speedText;

    public SystemDetailView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        dateView = new DateView();
    }

    @Override
    public void start()
    {
        distanceText = "Distance : ";

        speedText = "Speed : ";


        paneBox = createHBox(width, height, 50, new Insets(15, 12, 15, 12));


        distanceLabel = createLabel(distanceText);

        speedLabel = createLabel(speedText);

        dateLabel = dateView.getLabel();


        paneBox.getChildren().add(distanceLabel);

        paneBox.getChildren().add(speedLabel);

        paneBox.getChildren().add(dateLabel);

        
        getChildren().add(paneBox);
    }   

    @Override
    public void update()
    {
        dateView.update();

        distanceLabel.setText(distanceText);

        speedLabel.setText(speedText);
    }
}
package group9.project;

import javafx.scene.control.Label;

public class SystemDetailView extends PaneView
{
    public SystemDetailView(int parentWidth, int parentHeight, int widthPercentage, int heightPercentage)
    {
        super(parentWidth, parentHeight, widthPercentage, heightPercentage);
        
        start();
    }

    @Override
    public void start()
    {
        Label newLabel = new Label("Teeeeest Label");
        
        getChildren().add(newLabel);
    }   

    @Override
    public void update()
    {
        
    }
}
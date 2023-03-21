package group9.project;

import javafx.scene.control.Label;

public class SystemDetailView extends PaneView
{
    public SystemDetailView(int width, int height)
    {
        super(width, height);
        
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
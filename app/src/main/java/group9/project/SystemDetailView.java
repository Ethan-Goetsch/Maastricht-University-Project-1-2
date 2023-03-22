package group9.project;


import java.util.ArrayList;

import javafx.scene.control.Label;

public class SystemDetailView extends PaneView
{
    ArrayList<Label> labels;
    DateView dateView = new DateView();
    Label dateLabel;

    public SystemDetailView(int width, int height)
    {
        super(width, height);

        labels = new ArrayList<>();

        start();
    }

    @Override
    public void start()
    {

        dateView.update();
        dateLabel = dateView.getLabel();

        getChildren().add(dateLabel);
    }   

    @Override
    public void update()
    {
        dateView.update();

        getChildren().remove(dateLabel);
        getChildren().add(dateView.getLabel());

    }
}
package group9.project;

import javafx.scene.layout.Pane;

public abstract class PaneView extends Pane implements IStartable, IUpdateable
{
    protected double width, height;

    public PaneView(int newWidth, int newHeight)
    {
        width = newWidth;

        height = newHeight;

        setPrefSize(newWidth, newHeight);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void start();
}
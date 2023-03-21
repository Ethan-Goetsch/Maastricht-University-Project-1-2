package group9.project;

import javafx.scene.layout.Pane;

public abstract class PaneView extends Pane implements IStartable, IUpdateable
{
    public PaneView(int width, int height)
    {
        setPrefSize(width, height);
    }

    @Override
    public abstract void update();

    @Override
    public abstract void start();
}
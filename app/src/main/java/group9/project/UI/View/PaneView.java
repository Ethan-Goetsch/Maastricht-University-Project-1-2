package group9.project.UI.View;

import group9.project.Utility.Interfaces.IUpdateable;
import javafx.scene.layout.Pane;

public abstract class PaneView extends Pane implements IUpdateable
{
    protected double width, height;

    public PaneView(int newWidth, int newHeight)
    {
        width = newWidth;

        height = newHeight;

        setPrefSize(newWidth, newHeight);
    }

    protected abstract void start();

    @Override
    public abstract void update();
}
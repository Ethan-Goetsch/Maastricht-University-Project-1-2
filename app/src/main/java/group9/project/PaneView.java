package group9.project;

import javafx.scene.layout.Pane;

public abstract class PaneView extends Pane implements IStartable, IUpdateable
{
    public PaneView(int parentWidth, int parentHeight, int widthPercentage, int heightPercentage)
    {
        int width = (parentWidth / 100) * widthPercentage;

        int height = (parentHeight / 100) * heightPercentage;

        setPrefSize(width, height);
    }

    @Override
    public abstract void update(double timeDelta);

    @Override
    public abstract void start();
}
package group9.project;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class PaneView extends Pane implements IStartable, IUpdateable
{
    protected int width, height;

    public PaneView(int newWidth, int newHeight)
    {
        width = newWidth;

        height = newHeight;

        setPrefSize(newWidth, newHeight);

        start();
    }

    protected HBox createHBox(double width, double height, double spacing, Insets padding)
    {
        HBox newHBox = new HBox();

        newHBox.setSpacing(spacing);

        newHBox.setPadding(padding);

        return newHBox;
    }

    protected Label createLabel(String labelText)
    {
        Label newLabel = new Label(labelText);

        return newLabel;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void start();
}
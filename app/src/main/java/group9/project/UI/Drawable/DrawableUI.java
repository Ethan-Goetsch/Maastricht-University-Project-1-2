package group9.project.UI.Drawable;

import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public abstract class DrawableUI implements IDrawable
{
    protected Vector3 drawablePosition;

    protected Pane drawablePane;

    protected Label drawableLabel;

    protected double scale;

    public DrawableUI()
    {
        DrawableManager.getInstance().add(this);

        drawablePane = new Pane();

        scale = 1;
    }

    @Override
    public Node getDrawable()
    {
        return drawablePane;
    }

    public void update(Vector3 newDrawablePosition)
    {
        drawablePosition = newDrawablePosition;

        draw();
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(drawablePosition);

        drawablePane.setTranslateX(scaledVector.getX());

        drawablePane.setTranslateY(scaledVector.getY());
    }

    public void setScale(double newScale)
    {
        scale = newScale;
    }

    public abstract void createDrawableUI();
}
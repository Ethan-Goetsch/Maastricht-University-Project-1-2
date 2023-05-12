package group9.project.UI.Drawable;

import group9.project.Physics.Objects.PhysicsObject;
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

    protected String name;

    protected PhysicsObject physicsObject;

    public DrawableUI(String name, PhysicsObject physicsObject)
    {
        DrawableManager.getInstance().add(this);

        drawablePane = new Pane();

        this.name = name;

        this.physicsObject = physicsObject;
    }

    @Override
    public Node getDrawable()
    {
        return drawablePane;
    }

    public void update()
    {
        drawablePosition = ScaleConverter.worldToScreenPosition(physicsObject.getPosition());

        draw();
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(drawablePosition);

        drawablePane.setTranslateX(scaledVector.getX());

        drawablePane.setTranslateY(scaledVector.getY());
    }

    public abstract void createDrawableUI();
}
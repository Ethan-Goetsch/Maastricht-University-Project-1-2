package group9.project;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class RocketShipObject extends PhysicsObject
{
    private Circle shape;

    public RocketShipObject()
    {
        super();
    }

    @Override
    public void applyForce(Vector3 force)
    {
        super.update();
    }

    @Override
    public void start()
    {
        super.start();
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }
}
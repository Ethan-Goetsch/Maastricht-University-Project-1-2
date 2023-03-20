package group9.project;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject()
    {
        super();
    }

    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    @Override
    public void applyForce(Vector3 force)
    {
        super.applyForce(force);
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
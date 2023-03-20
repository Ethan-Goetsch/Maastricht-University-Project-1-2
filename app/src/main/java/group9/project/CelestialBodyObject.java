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
        velocity.add(acceleration);

        position.add(velocity);

        acceleration.multiplyBy(0);
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }
}
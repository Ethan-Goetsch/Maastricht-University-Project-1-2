package group9.project;

import javafx.scene.shape.Shape;

public abstract class PhysicsObject implements IStartable, IUpdateable, IDrawable
{
    protected Vector3 position, velocity, acceleration;

    protected double mass;

    public double getMass()
    {
        return mass;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration)
    {
        position = startingPosition;

        velocity = startingVelocity;

        acceleration = startingAcceleration;

        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    /*
     * Apply force on object
     */
    public void applyForce(Vector3 newForce)
    {
        newForce.divideBy(mass);

        acceleration.add(newForce);
    };

    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    @Override
    public abstract void start();

    /*
     * Update position of object
     */
    @Override
    public abstract void update();

    @Override
    public abstract Shape getShape();
}
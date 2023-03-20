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

    public PhysicsObject()
    {
        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    /*
     * Apply force on object
     */
    public void applyForce(Vector3 force)
    {
        force.divideBy(mass);

        acceleration.add(force);
    };

    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    @Override
    public void start()
    {
        position = new Vector3();

        velocity = new Vector3();

        acceleration = new Vector3();
    }

    /*
     * Update position of object
     */
    @Override
    public abstract void update();

    @Override
    public abstract Shape getShape();
}
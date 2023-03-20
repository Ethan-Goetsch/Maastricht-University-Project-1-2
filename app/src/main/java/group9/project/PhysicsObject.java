package group9.project;

import javafx.scene.shape.Shape;

public abstract class PhysicsObject implements IStartable, IUpdateable, IDrawable
{
    protected Vector3 position, velocity, acceleration;

    protected double mass;

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

    public double getMass()
    {
        return mass;
    }

    public Vector3 getPosition()
    {
        return position;
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
    public void update() 
    {
        velocity.add(acceleration);

        position.add(velocity);

        acceleration.multiplyBy(0);
    }

    @Override
    public abstract Shape getShape();
}
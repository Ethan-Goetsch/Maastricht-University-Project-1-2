package group9.project;

import javafx.scene.shape.Shape;

public abstract class PhysicsObject implements IUpdateable, IDrawable
{
    protected Vector3 position, velocity, acceleration;

    protected double mass;

    protected String name;

    public Vector3 getPosition()
    {
        return position;
    }

    public double getMass()
    {
        return mass;
    }

    public String getName()
    {
        return name;
    }

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double mass, String newName)
    {
        setPosition(startingPosition);

        setVelocity(startingVelocity);

        setAcceleration(startingAcceleration);

        setMass(mass);

        name = newName;

        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    /*
     * Apply force to an object
     */
    public void applyForce(Vector3 force)
    {
        force = force.divideBy(mass).multiplyBy(PhysicsEngine.STEP_TIME);

        //System.out.println(force.normalize().toString());
        System.out.println("force: " + force.getMagnitude());

        acceleration = acceleration.add(force);
    }

    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    public void setVelocity(Vector3 newVelocity)
    {
        velocity = newVelocity;
    }

    public void setAcceleration(Vector3 newAcceleration)
    {
        acceleration = newAcceleration;
    }

    public void setMass(double newMass) 
    {
        mass = newMass;
    }

    /*
     * Update position of an object
     */
    @Override
    public abstract void update();

    @Override
    public abstract Shape getShape();

    @Override
    public abstract void setShapePosition();

    public boolean equals(PhysicsObject otherObject)
    {
        return otherObject.getName().equals(name);
    }
}
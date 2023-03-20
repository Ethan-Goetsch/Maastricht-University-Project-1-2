package group9.project;

import javafx.scene.shape.Shape;

public abstract class PhysicsObject implements IStartable, IUpdateable, IDrawable
{
    protected Vector3 position, velocity, acceleration;

    protected double mass;
    protected String name;

    public double getMass()
    {
        return mass;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, String name)
    {
        position = startingPosition;

        velocity = startingVelocity;

        acceleration = startingAcceleration;

        this.name = name;
        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    /*
     * Apply force on object
     */
    public void applyForce(Vector3 force) {
        force = force.divideBy(mass);
        //System.out.println(force.normalize().toString());
        System.out.println("force: " + force.getMagnitude());
        acceleration = acceleration.add(force);
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
    public void update(double timeDelta) 
    {
        velocity = velocity.add(acceleration).multiplyBy(timeDelta);
        //System.out.println(name + " pos: " + pos);
        pos = pos.add(velocity);
        //System.out.println(name + " pos: " + pos);
        acceleration = acceleration.multiplyBy(0);
    }

    public String getName() {
        return name;
    }

    public boolean equals(PhysicsObject o) {
        return o.getName().equals(name);
    }

}
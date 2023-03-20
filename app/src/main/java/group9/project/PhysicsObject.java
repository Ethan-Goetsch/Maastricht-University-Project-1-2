package group9.project;

public abstract class PhysicsObject implements IStartable, IUpdateable
{
    protected Vector3 pos, velocity, acceleration;
    protected double mass;

    public PhysicsObject()
    {
        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    /*
     * Apply force on object
     */
    public void applyForce(Vector3 force) {
        force.divideBy(mass);
        acceleration.add(force);
    };

    public double getMass() {
        return mass;
    }

    public Vector3 getPosition() {
        return pos;
    }

    public abstract void start();

    /*
     * Update position of object
     */
    public void update() 
    {
        velocity.add(acceleration);
        pos.add(velocity);
        acceleration.multiplyBy(0);
    }
}
package group9.project;

public abstract class PhysicsObject implements IStartable, IUpdateable
{
    protected Vector3 pos, velocity, acceleration;

    public PhysicsObject()
    {
        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    public abstract void applyForce(Vector3 force);

    public abstract void start();

    public abstract void update();
}
package group9.project;

public abstract class PhysicsObject implements IStartable, IUpdateable
{
    public PhysicsObject()
    {
        PhysicsEngine.addPhysicsObjectToUpdate(this);
    }

    public abstract void start();

    public abstract void update();
}
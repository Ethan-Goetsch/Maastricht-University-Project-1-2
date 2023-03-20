package group9.project;

public abstract class PhysicsObject implements IStartable, IUpdateable
{
    protected Vector3 pos, velocity, acceleration;
    protected double mass;
    protected String name;

    public PhysicsObject(String name)
    {
        this.name = name;
        PhysicsEngine.addPhysicsObjectToUpdate(this);
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
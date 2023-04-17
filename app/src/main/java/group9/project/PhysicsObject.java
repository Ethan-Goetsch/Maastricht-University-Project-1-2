package group9.project;

import javafx.scene.Node;

public abstract class PhysicsObject implements IUpdateable, IDrawable
{
    protected Vector3 position, velocity, force, acceleration;

    protected double mass;

    protected DifferentialSolver differentialSolver;

    protected PhysicsObjectType physicsObjectType;

    public Vector3 getPosition()
    {
        return position;
    }

    public Vector3 getVelocity()
    {
        return velocity;
    }

    public Vector3 getForce()
    {
        return force;
    }

    public Vector3 getAcceleration()
    {
        return acceleration;
    }

    public double getMass()
    {
        return mass;
    }

    public PhysicsObjectType getPhysicsObjectType()
    {
        return physicsObjectType;
    }

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType)
    {
        setPosition(startingPosition);

        setVelocity(startingVelocity);

        setForce(new Vector3());

        setAcceleration(new Vector3());

        setMass(mass);


        differentialSolver = newDifferentialSolver;

        physicsObjectType = newPhysicsObjectType;


        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    public void setVelocity(Vector3 newVelocity)
    {
        velocity = newVelocity;
    }

    /*
     * Apply force to an object
     */
    public void setForce(Vector3 newForce)
    {
        force = newForce;
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
    public void update()
    {
        differentialSolver.solveEquation(this);
    }

    @Override
    public abstract Node getDrawable();

    @Override
    public abstract void draw();
}
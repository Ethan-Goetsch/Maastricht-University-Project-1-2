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

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType)
    {
        position = startingPosition;

        velocity = startingVelocity;


        setForce(new Vector3());

        setAcceleration(new Vector3());


        mass = newMass;

        differentialSolver = newDifferentialSolver;

        physicsObjectType = newPhysicsObjectType;


        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    private void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    private void setVelocity(Vector3 newVelocity)
    {
        velocity = newVelocity;
    }

    public void setForce(Vector3 newForce)
    {
        force = newForce;
    }

    public void setAcceleration(Vector3 newAcceleration)
    {
        acceleration = newAcceleration;
    }

    /**
     * Updates the Position and Velocity of this Object using its Differential Solver
    */
    @Override
    public void update()
    {
        setPosition(differentialSolver.solveEquation(getPosition(), getVelocity(), PhysicsEngine.getSimulationStepTime()));

        setVelocity(differentialSolver.solveEquation(getVelocity(), getAcceleration(), PhysicsEngine.getSimulationStepTime()));
    }

    @Override
    public abstract Node getDrawable();

    @Override
    public abstract void draw();
}
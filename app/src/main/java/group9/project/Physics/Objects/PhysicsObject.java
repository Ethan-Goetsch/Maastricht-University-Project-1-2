package group9.project.Physics.Objects;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Vector3;

public abstract class PhysicsObject implements IStartable, IUpdateable, ITargetable
{
    protected Vector3 position, prevPosition, velocity, force, acceleration;

    protected double mass;

    protected DifferentialSolver differentialSolver;

    protected PhysicsObjectType physicsObjectType;

    public Vector3 getPosition()
    {
        return position;
    }
    
    public Vector3 getDirection()
    {
        return position.subtract(prevPosition);
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

    public DifferentialSolver getDifferentialSolver()
    {
        return differentialSolver;
    }

    public PhysicsObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType)
    {
        position = startingPosition;
        prevPosition = position;

        velocity = startingVelocity;


        setForce(new Vector3());

        setAcceleration(new Vector3());


        mass = newMass;

        differentialSolver = newDifferentialSolver;

        physicsObjectType = newPhysicsObjectType;


        PhysicsEngine.getInstance().addPhysicsObjectToUpdate(this);
    }

    public void setPosition(Vector3 newPosition)
    {
        prevPosition = position;
        position = newPosition;
    }

    public void setVelocity(Vector3 newVelocity)
    {
        velocity = newVelocity;
    }

    public void applyVelocity(Vector3 newVelocity)
    {
        velocity = velocity.add(newVelocity);
    }

    public void setForce(Vector3 newForce)
    {
        force = newForce;
    }

    public void applyForce(Vector3 newForce)
    {
        force = force.add(newForce);
    }

    public void setAcceleration(Vector3 newAcceleration)
    {
        acceleration = newAcceleration;
    }

    @Override
    public abstract void start();

    /**
     * Updates the Position and Velocity of this Object using its Differential Solver
    */
    @Override
    public abstract void update();

}

package group9.project.Physics.Objects;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Vector3;

public abstract class PhysicsObject implements IStartable, IUpdateable, ITargetable
{
    protected Vector3 position, velocity, force, acceleration;

    protected double mass;

    protected DifferentialSolver differentialSolver;

    protected PhysicsObjectType physicsObjectType;

    /**
     * @return the current position of the Physisc Object
     */
    public Vector3 getPosition()
    {
        return position;
    }

    /**
     * @return the current velocity of the Physisc Object
     */
    public Vector3 getVelocity()
    {
        return velocity;
    }

    /**
     * @return the current force of the Physisc Object
     */
    public Vector3 getForce()
    {
        return force;
    }

    /**
     * @return the current acceleration of the Physisc Object
     */
    public Vector3 getAcceleration()
    {
        return acceleration;
    }

    /**
     * @return the mass of the Physisc Object
     */
    public double getMass()
    {
        return mass;
    }

    /**
     * @return the object's Physics Object Type
     */
    public PhysicsObjectType getPhysicsObjectType()
    {
        return physicsObjectType;
    }

    /**
     * @return the object's Differential Solver
     */
    public DifferentialSolver getDifferentialSolver()
    {
        return differentialSolver;
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

    /**
     * Sets the Physics Objects position
     * 
     * @param newPosition the new position of the Physics Object
     */
    public void setPosition(Vector3 newPosition)
    {
        position = newPosition;
    }

    /**
     * Sets the Physics Objects velocity
     * 
     * @param newVelocity the new velocty of the Physics Object
     */
    public void setVelocity(Vector3 newVelocity)
    {
        velocity = newVelocity;
    }

    /**
     * Adds velocity to the Physics Object
     * 
     * @param newVelocity the velocity to add to the Physics Object
     */
    public void applyVelocity(Vector3 newVelocity)
    {
        velocity = velocity.add(newVelocity);
    }

    /**
     * Sets the Physics Objects force
     * 
     * @param newForce the new force of the Physics Object
     */
    public void setForce(Vector3 newForce)
    {
        force = newForce;
    }

    /**
     * Adds force to the Physics Object
     * 
     * @param newForce the force to add to the Physics Object
     */
    public void applyForce(Vector3 newForce)
    {
        force = force.add(newForce);
    }

    /**
     * Sets the Physics Objects position
     * 
     * @param newPosition the new position of the Physics Object
     */
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

    @Override
    public String getDescription()
    {
        return physicsObjectType.toString();
    }

}

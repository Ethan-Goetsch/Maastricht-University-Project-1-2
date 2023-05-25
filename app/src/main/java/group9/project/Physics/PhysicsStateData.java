package group9.project.Physics;

import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Vector3;

public class PhysicsStateData implements IUpdateable
{
    private PhysicsObject physicsObject;

    private PhysicsObjectType physicsObjectType;

    private Vector3 currentPosition;

    private Vector3 currentVelocity;

    private Vector3 currentForce;

    private Vector3 currentAcceleration;

    /**
     * @return the {Physics Object
     */
    public PhysicsObject getPhysicsObject()
    {
        return physicsObject;
    }
    
    /**
     * @return the physics object type of the Physics Object
     */
    public PhysicsObjectType getPhysicsObjectType()
    {
        return physicsObjectType;
    }

    /**
     * @return the current position of the Physics Object
     */
    public Vector3 getCurrentPosition()
    {
        return currentPosition;
    }

    /**
     * @return the current velocity of the Physics Object
     */
    public Vector3 getCurrentVelocity()
    {
        return currentVelocity;
    }

    /**
     * @return the current force of the Physics Object
     */
    public Vector3 getCurrentForce()
    {
        return currentForce;
    }

    /**
     * @return the current acceleration of the Physics Object
     */
    public Vector3 getCurrentAcceleration()
    {
        return currentAcceleration;
    }

    public PhysicsStateData(PhysicsObject newPhysicsObject)
    {
        physicsObject = newPhysicsObject;

        physicsObjectType = newPhysicsObject.getPhysicsObjectType();

        update();
    }

    /**
     * Sets the current position, velocity, force and acceleration based on the Physics Object's respective values
     */
    @Override
    public void update()
    {
        currentPosition = physicsObject.getPosition();

        currentVelocity = physicsObject.getVelocity();

        currentForce = physicsObject.getForce();
        
        currentAcceleration = physicsObject.getAcceleration();
    }
}
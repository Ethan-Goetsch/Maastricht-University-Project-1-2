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

    public PhysicsStateData(PhysicsObject newPhysicsObject)
    {
        physicsObject = newPhysicsObject;

        physicsObjectType = newPhysicsObject.getPhysicsObjectType();

        update();
    }

    @Override
    public void update()
    {
        currentPosition = physicsObject.getPosition();

        currentVelocity = physicsObject.getVelocity();

        currentForce = physicsObject.getForce();
        
        currentAcceleration = physicsObject.getAcceleration();
    }

    public PhysicsObject getPhysicsObject()
    {
        return physicsObject;
    }

    public PhysicsObjectType getPhysicsObjectType()
    {
        return physicsObjectType;
    }

    public Vector3 getCurrentPosition()
    {
        return currentPosition;
    }

    public Vector3 getCurrentVelocity()
    {
        return currentVelocity;
    }

    public Vector3 getCurrentForce()
    {
        return currentForce;
    }

    public Vector3 getCurrentAcceleration()
    {
        return currentAcceleration;
    }
}
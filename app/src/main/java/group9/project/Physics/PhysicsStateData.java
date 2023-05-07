package group9.project.Physics;

import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Utility.Math.Vector3;

public class PhysicsStateData
{
    private Vector3 currentPosition;

    private Vector3 currentVelocity;

    private Vector3 currentForce;

    private Vector3 currentAcceleration;

    public PhysicsStateData(Vector3 newCurrentPosition, Vector3 newCurrentVelocity, Vector3 newCurrentForce, Vector3 newCurrentAcceleration)
    {
        currentPosition = newCurrentPosition;

        currentVelocity = newCurrentVelocity;

        currentForce = newCurrentForce;
        
        currentAcceleration = newCurrentAcceleration;
    }

    public PhysicsStateData(PhysicsObject physicsObject)
    {
        currentPosition = physicsObject.getPosition();

        currentVelocity = physicsObject.getVelocity();

        currentForce = physicsObject.getForce();
        
        currentAcceleration = physicsObject.getAcceleration();
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
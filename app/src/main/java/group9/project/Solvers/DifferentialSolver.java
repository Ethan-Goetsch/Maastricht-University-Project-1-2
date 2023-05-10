package group9.project.Solvers;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Math.Vector3;

public abstract class DifferentialSolver
{
    /**
    * Solves and returns the answer to a differential equation
    *   
    * @param position initial y value
    * @param velocity velocity
    * @param acceleration acceleration
    * @param h the Step Time to use in the calculation
    * 
    * @return the next position and velocity values
    */
    public abstract Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType);

    protected Vector3 getVelocityAtPoint(Vector3 initialValue, Vector3 derivative, double h)
    {
        return initialValue.add(derivative.multiplyBy(h));
    }

    protected Vector3 getAccelerationAtPoint(double h, PhysicsObjectType physicsObjectType)
    {
        return PhysicsEngine.getInstance().calculateAcceleration(h, physicsObjectType);
    }
}
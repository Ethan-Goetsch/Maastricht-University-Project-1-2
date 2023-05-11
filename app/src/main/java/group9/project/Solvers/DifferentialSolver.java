package group9.project.Solvers;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Interfaces.INumericalFunction;
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
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType)
    {
        Vector3[] state = new Vector3[2];


        INumericalFunction<Double, Vector3, Vector3> velocityAtPoint = (t, w) -> getVelocityAtPoint(velocity, acceleration, t);

        INumericalFunction<Double, Vector3, Vector3> accelerationAtPoint = (t, w) -> getAccelerationAtPoint(t, physicsObjectType);


        state[0] = differentialAlgorithm(position, velocityAtPoint, h, 0);

        state[1] = differentialAlgorithm(velocity, accelerationAtPoint, h, 0);

        
        return state;
    }

    protected Vector3 getVelocityAtPoint(Vector3 initialValue, Vector3 derivative, double h)
    {
        return initialValue.add(derivative.multiplyBy(h));
    }

    protected Vector3 getAccelerationAtPoint(double h, PhysicsObjectType physicsObjectType)
    {
        return PhysicsEngine.getInstance().calculateAcceleration(h, physicsObjectType);
    }

    public abstract Vector3 differentialAlgorithm(Vector3 initialValue, INumericalFunction<Double, Vector3, Vector3> derivativeFunction, double h, double t);
}
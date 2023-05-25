package group9.project.Solvers;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Interfaces.INumericalMethod;
import group9.project.Utility.Math.Vector3;

public abstract class DifferentialSolver implements INumericalMethod<Vector3>
{
    /**
    * Solves and returns the answer to a differential equation using the Physics Engine
    *   
    * @param position position of the Physics Object
    * @param velocity velocity of the Physics Object
    * @param acceleration acceleration of th ePhyscis Object
    * @param h the Step Time to use in the calculation
    * 
    * @return the next position and velocity values of the Physics Object
    */
    public Vector3[] solvePhysicsEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType)
    {
        Vector3[] state = new Vector3[2];


        INumericalFunction<Double, Vector3> velocityAtPoint = (t, w) -> getVelocityAtPoint(velocity, acceleration, t);

        INumericalFunction<Double, Vector3> accelerationAtPoint = (t, w) -> getAccelerationAtPoint(t, physicsObjectType);


        state[0] = solveNumericalEquation(position, velocityAtPoint, h, 0);

        state[1] = solveNumericalEquation(velocity, accelerationAtPoint, h, 0);

        
        return state;
    }

    /**
    * Solves and returns the answer to a differential equation
    *   
    * @param initialValue initial velocity value
    * @param derivative velocity's derivative value
    * @param h the time you want to calculate the velocity for. Also the Step Size used in the calculations
    * 
    * @return the velocity at that point in time
    */
    protected Vector3 getVelocityAtPoint(Vector3 initialValue, Vector3 derivative, double h)
    {
        return initialValue.add(derivative.multiplyBy(h));
    }

    /**
    * returns the acceleration of a Physics Object at a point in time
    *   
    * @param physicsObjectType the type of the object you want to return the acceleration of
    * @param h the time you want to calculate the acceleration for. Also the Step Size used in the calculations
    * 
    * @return the acceleration at that point in time
    */
    protected Vector3 getAccelerationAtPoint(double h, PhysicsObjectType physicsObjectType)
    {
        return PhysicsEngine.getInstance().calculateAcceleration(h, physicsObjectType);
    }

    /**
    * Solves and returns the answer to a differential equation using a Numerical Method
    *   
    * @param initialValue the initial value of the equation
    * @param derivativeFunction the derivative equation
    * @param h the Step Time to use in the calculation
    * @param t the current time to use in the derivative calculation
    * 
    * @return the next position and velocity values
    */
    public abstract Vector3 solveNumericalEquation(Vector3 initialValue, INumericalFunction<Double, Vector3> derivativeFunction, double h, double t);
}
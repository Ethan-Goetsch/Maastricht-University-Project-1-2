package group9.project.Solvers;

import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Math.Vector3;

public abstract class DifferentialSolver
{
    /**
    * Solves and returns the answer to a differential equation using Euler's Method
    * 
    * @param initialY initial y value
    * @param function the derivative
    * @param h the Step Time to use in the calculation
    * 
    * @return the next y value
    */
    // This was put here so that the other solvers can access and use Euler's Method easily
    // Eulers Solver is in another class so that it is easy to test and change different solver's without having to change code by changing a Physics Object's Differential Solver
    protected Vector3 solveEulerEquation(Vector3 initialY, Vector3 derivative, double h)
    {
        return initialY.add(derivative.multiplyBy(h));
    }

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
}
package group9.project.Solvers;

import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Math.Vector3;

public class EulerSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType)
    {
        Vector3[] state = new Vector3[2];

        state[0] = eulerAlgorithm(position, velocity, h);

        state[1] = eulerAlgorithm(velocity, acceleration, h);

        return state;
    }   

    /**
    * Solves and returns the answer to a differential equation using Euler's Method
    * 
    * @param initialValue initial value
    * @param derivative the derivative of y
    * @param h the Step Time to use in the calculation
    * 
    * @return the next y value
    */
    public Vector3 eulerAlgorithm(Vector3 initialValue, Vector3 derivative, double h)
    {
        return initialValue.add(derivative.multiplyBy(h));
    }
}
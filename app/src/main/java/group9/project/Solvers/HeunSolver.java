package group9.project.Solvers;

import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class HeunSolver extends DifferentialSolver
{
    /**
    * Solves and returns the answer to a differential equation using the Heun's Method
    *   
    * @param initialValue the initial value of the equation
    * @param derivativeFunction the derivative equation
    * @param h the Step Time to use in the calculation
    * @param t the current time to use in the derivative calculation
    * 
    * @return the next position and velocity values
    */
    @Override
    public Vector3 solveNumericalEquation(Vector3 initialValue, INumericalFunction<Double, Vector3> derivativeFunction, double h, double t)
    {
        Vector3 k1 = derivativeFunction.evaluate(t, initialValue).multiplyBy(h);

        Vector3 k2 = derivativeFunction.evaluate(t + 1 / 3.0 * h, initialValue.add(k1.multiplyBy(1 / 3.0))).multiplyBy(h);

        Vector3 k3 = derivativeFunction.evaluate(t + 2 / 3.0 * h, initialValue.add(k2.multiplyBy(2 / 3.0))).multiplyBy(h);


        Vector3 valueSum = k1.add(k3.multiplyBy(3)).multiplyBy(1 / 4.0);

        Vector3 nextValue = initialValue.add(valueSum);

        return nextValue;
    }
}
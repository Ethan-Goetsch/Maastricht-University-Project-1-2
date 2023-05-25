package group9.project.Solvers;

import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class EulerSolver extends DifferentialSolver
{
    /**
    * Solves and returns the answer to a differential equation using the Euler Method
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
        return initialValue.add(derivativeFunction.evaluate(t, initialValue).multiplyBy(h));
    }
}
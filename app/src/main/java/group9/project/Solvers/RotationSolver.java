package group9.project.Solvers;

import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Interfaces.INumericalMethod;

public class RotationSolver implements INumericalMethod<Double>
{
    @Override
    public Double solveNumericalEquation(Double initialValue, INumericalFunction<Double, Double> derivativeFunction, double h, double t)
    {
        double k1 = derivativeFunction.evaluate(t, initialValue) * h;

        double k2 = derivativeFunction.evaluate(t + 1 / 3.0 * h, initialValue + k1 * (1 / 3.0)) * h;

        double k3 = derivativeFunction.evaluate(t + 2 / 3.0 * h, initialValue + k1 * (-1 / 3.0) + (k2)) * h;

        double k4 = derivativeFunction.evaluate(t + h, initialValue + (k1 - k2 + k3)) * h;


        double valueSum = (k1 + (k2 * 3) + (k3 * 3) + k4) / (1 / 8.0);

        double nextValue = initialValue + valueSum;

        return nextValue;
    } 
}
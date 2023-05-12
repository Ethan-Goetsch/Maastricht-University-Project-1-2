package group9.project.Solvers;

import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class EulerSolver extends DifferentialSolver
{
    @Override
    public Vector3 solveNumericalEquation(Vector3 initialValue, INumericalFunction<Double, Vector3> derivativeFunction, double h, double t)
    {
        return initialValue.add(derivativeFunction.evaluate(t, initialValue).multiplyBy(h));
    }
}
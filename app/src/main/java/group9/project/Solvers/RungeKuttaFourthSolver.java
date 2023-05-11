package group9.project.Solvers;

import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class RungeKuttaFourthSolver extends DifferentialSolver
{
    @Override
    public Vector3 differentialAlgorithm(Vector3 initialValue, INumericalFunction<Double, Vector3, Vector3> derivativeFunction, double h, double t)
    {
        Vector3 k1 = derivativeFunction.evaluate(t, initialValue).multiplyBy(h);

        Vector3 k2 = derivativeFunction.evaluate(t + 1 / 3.0 * h, initialValue.add(k1.multiplyBy(1 / 3.0))).multiplyBy(h);

        Vector3 k3 = derivativeFunction.evaluate(t + 2 / 3.0 * h, initialValue.add(k1.multiplyBy(-1 / 3.0).add(k2))).multiplyBy(h);

        Vector3 k4 = derivativeFunction.evaluate(t + h, initialValue.add(k1.subtract(k2).add(k3))).multiplyBy(h);


        Vector3 valueSum = k1.add(k2.multiplyBy(3).add(k3.multiplyBy(3).add(k4))).multiplyBy(1 / 8.0);

        Vector3 nextValue = initialValue.add(valueSum);

        return nextValue;
    }
}
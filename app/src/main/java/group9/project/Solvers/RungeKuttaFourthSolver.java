package group9.project.Solvers;

import java.util.function.Function;

import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Math.Vector3;

public class RungeKuttaFourthSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType)
    {
        Vector3[] state = new Vector3[2];


        Function<Double, Vector3> velocityAtPoint = x -> getVelocityAtPoint(velocity, acceleration, x);

        Function<Double, Vector3> accelerationAtPoint = x -> getAccelerationAtPoint(x, physicsObjectType);


        state[0] = rungeKuttaAlgorithm(position, velocity, velocityAtPoint, h);

        state[1] = rungeKuttaAlgorithm(velocity, acceleration, accelerationAtPoint, h);


        return state;
    }

    public Vector3 rungeKuttaAlgorithm(Vector3 initialValue, Vector3 derivative, Function<Double, Vector3> derivativeFunction, double h)
    {
        Vector3 k1V = derivative.multiplyBy(h);

        Vector3 k2V = derivativeFunction.apply(1 / 3.0 * h).multiplyBy(h);

        Vector3 k3V = derivativeFunction.apply(2 / 3.0 * h).multiplyBy(h);

        Vector3 k4V = derivativeFunction.apply(h).multiplyBy(h);


        Vector3 valueSum = k1V.add(k2V.multiplyBy(3).add(k3V.multiplyBy(3).add(k4V))).multiplyBy(1 / 8.0);

        Vector3 nextValue = initialValue.add(valueSum);

        return nextValue;
    }
}
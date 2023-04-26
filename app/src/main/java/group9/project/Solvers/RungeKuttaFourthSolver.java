package group9.project.Solvers;

import group9.project.Utility.Math.Vector3;

public class RungeKuttaFourthSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h)
    {
        Vector3[] state = new Vector3[2];


        Vector3 k1V = acceleration.multiplyBy(h);

        Vector3 k2V = solveEulerEquation(velocity, acceleration, 1 / 3.0 * h).multiplyBy(h);

        Vector3 k3V = solveEulerEquation(velocity, acceleration, 2 / 3.0 * h).multiplyBy(h);

        Vector3 k4V = solveEulerEquation(velocity, acceleration, h).multiplyBy(h);


        Vector3 k1P = position.multiplyBy(h);

        Vector3 k2P = solveEulerEquation(velocity, acceleration, 1 / 3.0 * h).multiplyBy(h);

        Vector3 k3P = solveEulerEquation(velocity, acceleration, 2 / 3.0 * h).multiplyBy(h);

        Vector3 k4P = solveEulerEquation(velocity, velocity, h).multiplyBy(h);


        Vector3 velocitySum = k1V.add(k2V.multiplyBy(3).add(k3V.multiplyBy(3).add(k4V))).multiplyBy(1 / 8.0);

        Vector3 nextVelocity = velocity.add(velocitySum);
        

        Vector3 positionSum = k1P.add(k2P.multiplyBy(3).add(k3P.multiplyBy(3).add(k4P))).multiplyBy(1 / 8.0);

        Vector3 nextPosition = velocity.add(positionSum);


        state[0] = nextPosition;

        state[1] = nextVelocity;


        return state;
    }
}
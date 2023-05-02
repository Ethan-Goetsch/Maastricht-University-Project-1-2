package group9.project.Solvers;

import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Utility.Math.Vector3;

public class HeunSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h, PhysicsObjectType physicsObjectType)
    {
        Vector3[] state = new Vector3[2];


        Vector3 k1V = acceleration.multiplyBy(h);

        Vector3 k3V = getAccelerationAtPoint(2 / 3.0 * h, physicsObjectType).multiplyBy(h);


        Vector3 k1P = velocity.multiplyBy(h);

        Vector3 k3P = getVelocityAtPoint(velocity, acceleration, 2 / 3.0 * h).multiplyBy(h);


        Vector3 velocitySum = k1V.add(k3V.multiplyBy(3)).multiplyBy(1 / 4.0);

        Vector3 nextVelocity = velocity.add(velocitySum);
        

        Vector3 positionSum = k1P.add(k3P.multiplyBy(3)).multiplyBy(1 / 4.0);

        Vector3 nextPosition = position.add(positionSum);


        state[0] = nextPosition;

        state[1] = nextVelocity;

        return state;
    }
}
package group9.project;

public class RungeKuttaFourthSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h)
    {
        Vector3[] state = new Vector3[2];


        Vector3 k1Position = solveEulerEquation(position, velocity, h);
        
        Vector3 k2Position = solveEulerEquation(position.add(k1Position.multiplyBy(1 / 2.0)), solveEulerEquation(velocity, acceleration, h / 2), h / 2);

        Vector3 k3Position = solveEulerEquation(position.add(k2Position.multiplyBy(1 / 2.0)), solveEulerEquation(velocity, acceleration, h / 2), h / 2);

        Vector3 k4Position = solveEulerEquation(position.add(k3Position), solveEulerEquation(velocity, acceleration, h), h);


        Vector3 k1Velocity = solveEulerEquation(velocity, acceleration, h);

        Vector3 k2Velocity = solveEulerEquation(velocity.add(k1Velocity.multiplyBy(1 / 2.0)), acceleration, h / 2);

        Vector3 k3Velocity = solveEulerEquation(velocity.add(k2Velocity.multiplyBy(1 / 2.0)), acceleration, h / 2);

        Vector3 k4Velocity = solveEulerEquation(velocity.add(k3Velocity), acceleration, h);


        k2Position = k2Position.multiplyBy(2);

        k3Position = k3Position.multiplyBy(2);


        k2Velocity = k2Velocity.multiplyBy(2);

        k3Velocity = k3Velocity.multiplyBy(2);


        Vector3 nextPosition = position.add(((k1Position.add(k2Position).add(k3Position).add(k4Position).multiplyBy(1 / 6.0)).multiplyBy(h)));

        Vector3 nextVelocity = velocity.add(((k1Velocity.add(k2Velocity).add(k3Velocity).add(k4Velocity).multiplyBy(1 / 6.0)).multiplyBy(h)));

        
        state[0] = nextPosition;

        state[1] = nextVelocity;


        return state;
    }   
}
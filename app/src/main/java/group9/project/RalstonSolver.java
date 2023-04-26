package group9.project;

public class RalstonSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h)
    {
        Vector3[] state = new Vector3[2];
    
        int a = 2/4; // a = 1/2 gives you the midpoint method
                     // a = 1 gives you the modified Euler method 
                     // a = 2/3 is the standart Ralston's method


        Vector3 k1Position = solveEulerEquation(position, velocity, h);

        Vector3 k2Position = solveEulerEquation(position.add(k1Position.multiplyBy(a)), solveEulerEquation(velocity, acceleration, h) , h);

        Vector3 k1Velocity = solveEulerEquation(velocity, acceleration, h);

        Vector3 k2Velocity = solveEulerEquation(velocity.add(k1Position.multiplyBy(a)), solveEulerEquation(velocity, acceleration, h) , h);

        k1Position.multiplyBy(1-(1/ 2* a));

        k2Position.multiplyBy(1 / 2* a);

        k1Velocity.multiplyBy(1-(1/ 2* a));

        k2Velocity.multiplyBy(1 / 2* a);

        Vector3 nextPosition = position.add(k1Position).add(k2Position);

        Vector3 nextVelocity = velocity.add(k1Velocity).add(k2Velocity);

        state[0] = nextPosition;

        state[1] = nextVelocity;

        return state;
    }   
}
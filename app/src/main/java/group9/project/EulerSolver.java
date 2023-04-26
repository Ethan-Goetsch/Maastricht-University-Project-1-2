package group9.project;

public class EulerSolver extends DifferentialSolver
{
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h)
    {
        Vector3[] state = new Vector3[2];

        state[0] = solveEulerEquation(position, velocity, h);

        state[1] = solveEulerEquation(velocity, acceleration, h);

        return state;
    }   
}
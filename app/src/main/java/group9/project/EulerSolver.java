package group9.project;

public class EulerSolver extends DifferentialSolver
{
    // The Euler Equation is in the parent class : Differential Solver so that the other subclasses can access the formula
    // This is in another class so that it is easy to test and change different solver's without having to change code by changing a Physics Object's Differential Solver
    @Override
    public Vector3[] solveEquation(Vector3 position, Vector3 velocity, Vector3 acceleration, double h)
    {
        Vector3[] state = new Vector3[2];

        state[0] = solveEulerEquation(position, velocity, h);

        state[1] = solveEulerEquation(velocity, acceleration, h);

        return state;
    }   
}
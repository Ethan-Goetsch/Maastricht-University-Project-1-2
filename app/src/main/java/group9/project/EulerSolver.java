package group9.project;

public class EulerSolver extends DifferentialSolver
{
    @Override
    public Vector3 solveEquation(Vector3 initialY, Vector3 function, double h)
    {
        return initialY.add(function.multiplyBy(h));
    }   
}
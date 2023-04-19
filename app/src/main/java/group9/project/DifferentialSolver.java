package group9.project;

public abstract class DifferentialSolver
{
    /**
    * Solves and returns the answer to a differential equation using Euler's Method
    * 
    * @param initialY initial y value
    * @param function the differential equation of the initial y value
    * @param h the Step Time to use in the calculation
    * 
    * @return the next y value
    */
    // This was put here so that the other solvers can access and use Euler's Method easily
    // Eulers Solver is in another class so that it is easy to test and change different solver's without having to change code by changing a Physics Object's Differential Solver
    protected Vector3 solveEulerEquation(Vector3 initialY, Vector3 function, double h)
    {
        return initialY.add(function.multiplyBy(h));
    }

    /**
    * Solves and returns the answer to a differential equation
    * 
    * @param initialY initial y value
    * @param function the differential equation of the initial y value
    * @param h the Step Time to use in the calculation
    * 
    * @return the next y value
    */
    public abstract Vector3 solveEquation(Vector3 initialY, Vector3 function, double h);
}
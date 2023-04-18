package group9.project;

public abstract class DifferentialSolver
{
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
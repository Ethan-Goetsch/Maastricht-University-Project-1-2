package group9.project.Experiments;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.EulerSolver;
import group9.project.Solvers.HeunSolver;
import group9.project.Solvers.RalstonSolver;
import group9.project.Solvers.RungeKuttaFourthSolver;
import group9.project.Utility.Interfaces.INumericalFunction;
import group9.project.Utility.Math.Vector3;

public class Experiment
{
    private static final double STEP_SIZE = 0.01;

    private static final double MAX_TIME = 1;


    private static final Vector3 INITIAL_VALUE = new Vector3(2 / 3.0, 0, 0);

    private static final double INITIAL_TIME = 0;

    public static void main(String[] args)
    {
        INumericalFunction<Double, Vector3> function = new INumericalFunction<Double, Vector3>()
        {
            @Override
            public Vector3 evaluate(Double time, Vector3 value) 
            {
                double x = value.getX();

                return new Vector3(0.5 * Math.sqrt(x), 0, 0);
            }
        };
    
        INumericalFunction<Double, Double> exactFunction = new INumericalFunction<Double, Double>()
        {
            @Override
            public Double evaluate(Double time, Double value)
            {
                return Math.pow((time / 4 + 0.5),2);
            }
        };

        DifferentialSolver eulerSolver = new EulerSolver();

        DifferentialSolver ralstonSolver = new RalstonSolver();

        DifferentialSolver heunSolver = new HeunSolver();

        DifferentialSolver rungeKuttaSolver = new RungeKuttaFourthSolver();


        double currentYExact = INITIAL_VALUE.getX();

        Vector3 currentYEuler = INITIAL_VALUE;

        Vector3 currentYRalston = INITIAL_VALUE;

        Vector3 currentYHeun = INITIAL_VALUE;

        Vector3 currentYRungeKutta = INITIAL_VALUE;

        double currentTime = INITIAL_TIME;
        

        while (currentTime < MAX_TIME)
        {
            currentYEuler = eulerSolver.solveNumericalEquation(currentYEuler, function, STEP_SIZE, currentTime);

            currentYRalston = ralstonSolver.solveNumericalEquation(currentYRalston, function, STEP_SIZE, currentTime);

            currentYHeun = heunSolver.solveNumericalEquation(currentYHeun, function, STEP_SIZE, currentTime);

            currentYRungeKutta = rungeKuttaSolver.solveNumericalEquation(currentYRungeKutta, function, STEP_SIZE, currentTime);

            currentTime += STEP_SIZE;

            currentYExact = exactFunction.evaluate(currentTime, currentYExact);
            

            System.out.println(currentTime + " time");

            System.out.println( Math.abs((currentYEuler.getX() - currentYExact) / currentYExact));

            System.out.println( Math.abs((currentYRalston.getX() - currentYExact) / currentYExact));

            System.out.println( Math.abs((currentYHeun.getX() - currentYExact)/ currentYExact));

            System.out.println(Math.abs((currentYRungeKutta.getX() - currentYExact)/currentYExact));
        }
    }
}
package group9.project.Settings;

import group9.project.Data.Data;
import group9.project.Solvers.*;

public class PhysicsSettings
{
    private static final double UNIVERSE_TICK_TIME = 0.0001;

    private static final double MAX_UNIVERSE_TIME = Data.getMonthsAsSeconds(9);

    private static final double GRAVITY = 6.6743E-20;

    private static final double STEP_SIZE = 100;                                                                                                                                                                                                                                                                                                  ;

    private static final DifferentialSolver universeDifferentialSolver = new RungeKuttaFourthSolver();

    /**
     * @return the universe tick time
     */
    public static double getUniverseTickTime()
    {
        return UNIVERSE_TICK_TIME;
    }

    /**
     * @return the max universe time
     */
    public static double getMaxUniverseTime()
    {
        return MAX_UNIVERSE_TIME;
    }

    /**
     * @return gravity (?). Defined in the Manual
     */
    public static double getGravity()
    {
        return GRAVITY;
    }

    /**
     * @return the step size of the Physics Engine
     */
    public static double getStepSize()
    {
        return STEP_SIZE * SimulationSettings.getSimulationSpeed();
    }

    /**
     * @return the Differential Solver of the Physics Engine to use in Physics Object's calculation
     */
    public static DifferentialSolver getUniverseDifferentialSolver()
    {
        return universeDifferentialSolver;
    }
}
package group9.project.Settings;

import group9.project.Solvers.*;

public class PhysicsSettings
{
    private static final double UNIVERSE_TICK_TIME = 0.001;

    private static final double MAX_UNIVERSE_TIME = 0;

    private static final double GRAVITY = 6.6743E-20;

    private static final double STEP_TIME = 100;

    private static final DifferentialSolver universeDifferentialSolver = new HeunSolver();

    public static double getUniverseTickTime()
    {
        return UNIVERSE_TICK_TIME;
    }

    public static double getMaxUniverseTime()
    {
        return MAX_UNIVERSE_TIME;
    }

    public static double getGravity()
    {
        return GRAVITY;
    }

    public static double getStepTime()
    {
        return STEP_TIME * SimulationSettings.getSimulationSpeed();
    }

    public static DifferentialSolver getUniverseDifferentialSolver()
    {
        return universeDifferentialSolver;
    }
}
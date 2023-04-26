package group9.project;

public class PhysicsSettings
{
    private static final double UNIVERSE_TICK_TIME = 0.0001;

    private static final double MAX_UNIVERSE_TIME = 0;

    private static final double GRAVITY = 6.6743E-20;

    private static final double STEP_TIME = 10;

    private static final DifferentialSolver universeDifferentialSolver = new EulerSolver();

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

    public static double getSimulationStepTime()
    {
        return STEP_TIME * SimulationSettings.getSimulationSpeed();
    }

    public static DifferentialSolver getUniverseDifferentialSolver()
    {
        return universeDifferentialSolver;
    }
}
package group9.project;

public class SimulationSettings
{
    private static final double MIN_SIMULATION_SPEED = 0.01;

    private static final double MAX_SIMULATION_SPEED = 10;


    private static final double MIN_SCALE_FACTOR = 0.1;

    private static final double MAX_SCALE_FACTOR = 3;


    private static double simulationSpeed = 1;

    private static double simulationTime = 0;


    private static boolean isSimulationPaused;

    public static double getMinSimulationSpeed()
    {
        return MIN_SIMULATION_SPEED;
    }

    public static double getMaxSimulationSpeed()
    {
        return MAX_SIMULATION_SPEED;
    }

    public static double getMinScaleFactor()
    {
        return MIN_SCALE_FACTOR;
    }

    public static double getMaxScaleFactor()
    {
        return MAX_SCALE_FACTOR;
    }

    public static double getSimulationSpeed()
    {
        return simulationSpeed;
    }

    public static double getSimulationTime()
    {
        return simulationTime;
    }

    public static boolean getIsSimulationPaused()
    {
        return isSimulationPaused;
    }

    public static void setSimulationSpeed(double value)
    {
        simulationSpeed = value;
    }

    public static void updateSimulationTime(double value)
    {
        simulationTime += value;
    }

    public static void playOrPauseSimulation()
    {
        if (SimulationSettings.getIsSimulationPaused())
        {
            playSimulation();
        }
        else
        {
            pauseSimulation();
        }
    }

    public static void playSimulation()
    {
        isSimulationPaused = false;
    }

    public static void pauseSimulation()
    {
        isSimulationPaused = true;
    }
}
package group9.project;

public class SimulationSettings
{
    public static final double UNIVERSE_TICK_TIME = 0.0001;

    public static final double GRAVITY = 6.6743E-20;


    private static double simulationSpeed = 1;

    private static double simulationTime = 0;

    
    private static boolean isSimulationPaused;

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

    private static void playSimulation()
    {
        isSimulationPaused = false;
    }

    private static void pauseSimulation()
    {
        isSimulationPaused = true;
    }
}
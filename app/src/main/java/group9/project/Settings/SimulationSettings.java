package group9.project.Settings;

import group9.project.Events.Event;
import group9.project.Events.IEventListener;

public class SimulationSettings
{
    private static final boolean DEVELOPMENT_MODE = false;

    private static final double MIN_SIMULATION_SPEED = 0.01;

    private static final double MAX_SIMULATION_SPEED = 100;


    private static final double MIN_SCALE_FACTOR = 0.1;

    private static final double MAX_SCALE_FACTOR = 1.5;


    private static double simulationSpeed = 1;

    private static double simulationTime = 0;


    private static boolean isSimulationPaused;

    private static Event simulationPausedEvent = new Event();

    private static Event simulationCompletedEvent = new Event();

    public static boolean getDEVELOPMENT_MODE()
    {
        return DEVELOPMENT_MODE;
    }

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

        if (PhysicsSettings.getMaxUniverseTime() > 0 && simulationTime >= PhysicsSettings.getMaxUniverseTime())
        {
            pauseSimulation();

            completeSimulation();
        }
    }

    public static void playOrPauseSimulation()
    {
        if (SimulationSettings.getIsSimulationPaused())
        {
            unpauseSimulation();
        }
        else
        {
            pauseSimulation();
        }
    }

    public static void unpauseSimulation()
    {
        isSimulationPaused = false;
    }

    public static void pauseSimulation()
    {
        isSimulationPaused = true;

        simulationPausedEvent.raiseEvent();
    }

    private static void completeSimulation()
    {
        simulationCompletedEvent.raiseEvent();
    }

    public static void subscribeListenerToPausedEvent(IEventListener listener)
    {
        simulationPausedEvent.subscribeListener(listener);
    }

    public static void unsubscribeListenerFromPausedEvent(IEventListener listener)
    {
        simulationPausedEvent.unsubscribeListener(listener);
    }

    public static void subscribeListenerToCompletedEvent(IEventListener listener)
    {
        simulationCompletedEvent.subscribeListener(listener);
    }

    public static void unsubscribeListenerFromCompletedEvent(IEventListener listener)
    {
        simulationCompletedEvent.unsubscribeListener(listener);
    }
}
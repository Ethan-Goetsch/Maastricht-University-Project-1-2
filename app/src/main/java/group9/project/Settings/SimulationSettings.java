package group9.project.Settings;

import group9.project.Events.Event;
import group9.project.Events.IEventListener;
import group9.project.Optimization.OptimizationDevelopmentMode;

public class SimulationSettings
{
    private static final OptimizationDevelopmentMode OPTIMIZATION_DEVELOPMENT_MODE = OptimizationDevelopmentMode.None;


    private static final double MIN_SIMULATION_SPEED = 0.01;

    private static final double MAX_SIMULATION_SPEED = 100;


    private static final double MIN_SCALE_FACTOR = 0.1;

    private static final double MAX_SCALE_FACTOR = 1.5;


    private static double simulationSpeed = 1;

    private static double simulationTime = 0;


    private static boolean isSimulationPaused = true;

    private static boolean isCelestialBodiesPaused = false;


    private static Event simulationPlayedEvent = new Event();

    private static Event simulationPausedEvent = new Event();

    private static Event simulationCompletedEvent = new Event();

    /**
     * @return the development mode of the Optimization Algorithm
     */
    public static OptimizationDevelopmentMode getOptimizationDevelopmentMode()
    {
        return OPTIMIZATION_DEVELOPMENT_MODE;
    }

    /**
     * @return the minimum simulation speed
     */
    public static double getMinSimulationSpeed()
    {
        return MIN_SIMULATION_SPEED;
    }

    /**
     * @return the maximum simulation speed
     */
    public static double getMaxSimulationSpeed()
    {
        return MAX_SIMULATION_SPEED;
    }

    /**
     * @return the minimum scale factor
     */
    public static double getMinScaleFactor()
    {
        return MIN_SCALE_FACTOR;
    }

    /**
     * @return the maximum scale factor
     */
    public static double getMaxScaleFactor()
    {
        return MAX_SCALE_FACTOR;
    }

    /**
     * @return the current simulation speed
     */
    public static double getSimulationSpeed()
    {
        return simulationSpeed;
    }

    /**
     * @return the current simulation time
     */
    public static double getSimulationTime()
    {
        return simulationTime;
    }

    /**
     * @return true if the similation is paused
     */
    public static boolean getIsSimulationPaused()
    {
        return isSimulationPaused;
    }

    public static boolean isCelestialBodiesPaused()
    {
        return isCelestialBodiesPaused;
    }

    public static void setCelestialBodiesPaused(boolean newIsCelestialBodiesPaused)
    {
        isCelestialBodiesPaused = newIsCelestialBodiesPaused;
    }

    /**
     * Sets the simulation's speed
     * 
     * @param newSpeed the value to set the simulation's speed to
     */
    public static void setSimulationSpeed(double newSpeed)
    {
        simulationSpeed = newSpeed;
    }

    /**
     * Adds the value to the simulation time
     * 
     * @param value amount to add to the simulation time
     */
    public static void updateSimulationTime(double value)
    {
        simulationTime += value;

        if (PhysicsSettings.getMaxUniverseTime() > 0 && simulationTime >= PhysicsSettings.getMaxUniverseTime())
        {
            completeSimulation();
        }
    }

    /**
     * Plays the simulaiton if it is currently paused else pauses the simulation
     */
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

    /**
     * Plays the simulation and raises the simulation played event
     */
    public static void playSimulation()
    {
        isSimulationPaused = false;

        simulationPlayedEvent.raiseEvent();
    }

    /**
     * Pauses the simulation and raises the simulation paused event
     */
    public static void pauseSimulation()
    {
        isSimulationPaused = true;

        simulationPausedEvent.raiseEvent();
    }

    private static void completeSimulation()
    {
        simulationCompletedEvent.raiseEvent();
    }

    /**
     * Subscribes a listener to the Simulation Played Event
     * 
     * @param listener to subscribe to the played event
     */
    public static void subscribeListenerToPlayedEvent(IEventListener listener)
    {
        simulationPlayedEvent.subscribeListener(listener);
    }

    /**
     * Subscribes a listener to the Simulation Paused Event
     * 
     * @param listener to subsctive to the paused event
     */
    public static void subscribeListenerToPausedEvent(IEventListener listener)
    {
        simulationPausedEvent.subscribeListener(listener);
    }

    /**
     * Subscribes a listener to the Simulation Completed Event
     * 
     * @param listener to subscrive to the completed event
     */
    public static void subscribeListenerToCompletedEvent(IEventListener listener)
    {
        simulationCompletedEvent.subscribeListener(listener);
    }
}
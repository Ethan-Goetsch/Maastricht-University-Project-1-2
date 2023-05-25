package group9.project.Events;

import group9.project.Events.Listeners.PauseSimulationListener;
import group9.project.Events.Listeners.PlaySimulationListener;
import group9.project.Events.Listeners.ResetSimulationListener;
import group9.project.Settings.SimulationSettings;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.IUpdateable;

public class EventManager implements IStartable, IUpdateable
{
    //#region Singleton
    private static EventManager instance;

    public static EventManager getInstance()
    {
        if (instance == null)
        {
            instance = new EventManager();
        }

        return instance;
    }
    //#endregion

    private EventManager()
    {

    }

    @Override
    public void start()
    {
        attachEvents();
    }

    /**
     * Creates the Event Listeners and subscribes them to their Events
     */
    private void attachEvents()
    {
        SimulationSettings.subscribeListenerToPlayedEvent(new PlaySimulationListener());

        SimulationSettings.subscribeListenerToPausedEvent(new PauseSimulationListener());

        SimulationSettings.subscribeListenerToCompletedEvent(new ResetSimulationListener());
    }

    @Override
    public void update()
    {

    }
}
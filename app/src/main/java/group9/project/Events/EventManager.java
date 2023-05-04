package group9.project.Events;

import group9.project.Settings.SimulationSettings;
import group9.project.Utility.Interfaces.IStartable;

public class EventManager implements IStartable
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

    private void attachEvents()
    {
        SimulationSettings.subscribeListenerToCompletedEvent(new ResetSimulationListener());
    }
}
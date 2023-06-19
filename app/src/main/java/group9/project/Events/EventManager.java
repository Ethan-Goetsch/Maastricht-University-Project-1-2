package group9.project.Events;

import group9.project.Events.Listeners.ChangeCelestialBodiesPaused;
import group9.project.Events.Listeners.ChangeDrawableUIScale;
import group9.project.Events.Listeners.ChangeSimulationScale;
import group9.project.Events.Listeners.ChangeSimulationScaleSize;
import group9.project.Events.Listeners.ChangeSimulationSpeed;
import group9.project.Events.Listeners.ChangeSimulationWorldCentre;
import group9.project.Events.Listeners.PauseSimulation;
import group9.project.Events.Listeners.PlaySimulation;
import group9.project.Events.Listeners.ResetSimulation;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
import group9.project.States.Rocket.LandRocketState;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.IUpdateable;

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
        createSimulationEvents();

        UIEventManager.getInstance().start();
    }

    private void createSimulationEvents()
    {
        SimulationSettings.subscribeListenerToPlayedEvent(new PlaySimulation());

        SimulationSettings.subscribeListenerToPausedEvent(new PauseSimulation());

        SimulationSettings.subscribeListenerToCompletedEvent(new ResetSimulation());
    }
}
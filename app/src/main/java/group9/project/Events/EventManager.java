package group9.project.Events;

import group9.project.Events.Listeners.ChangeSimulationScaleListener;
import group9.project.Events.Listeners.ChangeSimulationScaleSize;
import group9.project.Events.Listeners.ChangeSimulationSpeedListener;
import group9.project.Events.Listeners.ChangeSimulationWorldCentreListener;
import group9.project.Events.Listeners.PauseSimulationListener;
import group9.project.Events.Listeners.PlaySimulationListener;
import group9.project.Events.Listeners.ResetSimulationListener;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
import group9.project.States.Rocket.LandRocketState;
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


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeedListener(0.01));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScaleListener(0.001));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScaleSize(5));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationWorldCentreListener(PhysicsObjectData.getInstance().getTitanObject()));


        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationSpeedListener(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScaleListener(0.5));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScaleSize(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationWorldCentreListener(PhysicsObjectData.getInstance().getSunObject()));
    }

    @Override
    public void update()
    {

    }
}
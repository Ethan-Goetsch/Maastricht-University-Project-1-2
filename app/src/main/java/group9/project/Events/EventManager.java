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
        SimulationSettings.subscribeListenerToPlayedEvent(new PlaySimulation());

        SimulationSettings.subscribeListenerToPausedEvent(new PauseSimulation());

        SimulationSettings.subscribeListenerToCompletedEvent(new ResetSimulation());


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeed(1));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScale(0.0001));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScaleSize(5));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeCelestialBodiesPaused(true));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(PhysicsObjectData.getInstance().getRocketShipObject().getDrawableUI(), 1));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(PhysicsObjectData.getInstance().getTitanObject().getDrawableUI(), 20));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationWorldCentre(PhysicsObjectData.getInstance().getTitanObject()));


        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationSpeed(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScale(0.5));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScaleSize(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeCelestialBodiesPaused(false));

        LandRocketState.subscribeListenerToExitEvent(new ChangeDrawableUIScale(PhysicsObjectData.getInstance().getRocketShipObject().getDrawableUI(), 1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeDrawableUIScale(PhysicsObjectData.getInstance().getTitanObject().getDrawableUI(), 1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationWorldCentre(PhysicsObjectData.getInstance().getSunObject()));
    }

    @Override
    public void update()
    {

    }
}
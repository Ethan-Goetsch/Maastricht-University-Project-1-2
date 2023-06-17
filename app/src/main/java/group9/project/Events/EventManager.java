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
        createSimulationEvents();

        createOnEnterLandRocketEvents();

        createOnExitLandRocketEvents();
    }

    private void createSimulationEvents()
    {
        SimulationSettings.subscribeListenerToPlayedEvent(new PlaySimulation());

        SimulationSettings.subscribeListenerToPausedEvent(new PauseSimulation());

        SimulationSettings.subscribeListenerToCompletedEvent(new ResetSimulation());
    }

    private void createOnEnterLandRocketEvents()
    {
        double animationDuration = 8;


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeed(0.01));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeCelestialBodiesPaused(true));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationWorldCentre(PhysicsObjectData.getInstance().getTitanObject()));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getRocketShipObject().getDrawableUI(), 0.25));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getTitanObject().getDrawableUI(), 20));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScale(0.000001));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScaleSize(10));
    }

    private void createOnExitLandRocketEvents()
    {
        double animationDuration = 5;


        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationSpeed(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeCelestialBodiesPaused(false));

    
        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationWorldCentre(PhysicsObjectData.getInstance().getSunObject()));


        LandRocketState.subscribeListenerToExitEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getRocketShipObject().getDrawableUI(), 1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getTitanObject().getDrawableUI(), 1));


        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScale(0.5));

        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationScaleSize(1));
    }

    @Override
    public void update()
    {

    }
}
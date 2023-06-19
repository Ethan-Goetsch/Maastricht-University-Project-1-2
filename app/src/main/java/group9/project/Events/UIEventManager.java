package group9.project.Events;

import group9.project.Events.Listeners.ChangeCelestialBodiesPaused;
import group9.project.Events.Listeners.ChangeSimulationSpeed;
import group9.project.States.Rocket.LandRocketState;
import group9.project.Utility.Interfaces.IStartable;

public class UIEventManager implements IStartable
{
    //#region Singleton
    private static UIEventManager instance;

    public static UIEventManager getInstance()
    {
        if (instance == null)
        {
            instance = new UIEventManager();
        }

        return instance;
    }
    //#endregion

    private UIEventManager()
    {

    }

    @Override
    public void start()
    {
        createOnEnterLandRocketUIEvents();

        createOnExitLandRocketUIEvents();
    }

    private void createOnEnterLandRocketUIEvents()
    {
        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeed(0.01));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeCelestialBodiesPaused(true));
    }

    private void createOnExitLandRocketUIEvents()
    {
        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationSpeed(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeCelestialBodiesPaused(false));
    }
}
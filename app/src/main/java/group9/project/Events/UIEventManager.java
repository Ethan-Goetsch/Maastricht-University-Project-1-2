package group9.project.Events;

import group9.project.Events.Listeners.ChangeCelestialBodiesPaused;
import group9.project.Events.Listeners.ChangeSimulationSpeed;
import group9.project.Events.Listeners.CinematicCameraEnterDirectState;
import group9.project.Events.Listeners.CinematicCameraEnterLandingState;
import group9.project.Events.Listeners.CinematicCameraEnterLaunchState;
import group9.project.Events.Listeners.CinematicCameraExitLandingState;
import group9.project.States.Rocket.DirectRocketState;
import group9.project.States.Rocket.LandRocketState;
import group9.project.States.Rocket.LaunchRocketState;
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
        createOnEnterLaunchRocketUIEvents();
        
        createOnEnterLandRocketUIEvents();

        createOnExitLandRocketUIEvents();
        
        createOnEnterDirectRocketUIEvents();
    }

    private void createOnEnterLandRocketUIEvents()
    {
        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeed(0.01));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeCelestialBodiesPaused(true));
        
        LandRocketState.subscribeListenerToEnterEvent(new CinematicCameraEnterLandingState());
    }

    private void createOnExitLandRocketUIEvents()
    {
        LandRocketState.subscribeListenerToExitEvent(new ChangeSimulationSpeed(1));

        LandRocketState.subscribeListenerToExitEvent(new ChangeCelestialBodiesPaused(false));
        
        LandRocketState.subscribeListenerToExitEvent(new CinematicCameraExitLandingState());
    }

    private void createOnEnterLaunchRocketUIEvents() {
        LaunchRocketState.subscribeListenerToEnterEvent(new CinematicCameraEnterLaunchState());
    }
    
    private void createOnEnterDirectRocketUIEvents()
    {
        DirectRocketState.subscribeListenerToEnterEvent(new CinematicCameraEnterDirectState());
    }
}
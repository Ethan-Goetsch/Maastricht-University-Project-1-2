package group9.project.Events;

import group9.project.Events.Listeners.ChangeCelestialBodiesPaused;
import group9.project.Events.Listeners.ChangeDrawableUIScale;
import group9.project.Events.Listeners.ChangeSimulationScale;
import group9.project.Events.Listeners.ChangeSimulationScaleSize;
import group9.project.Events.Listeners.ChangeSimulationSpeed;
import group9.project.Events.Listeners.ChangeSimulationWorldCentre;
import group9.project.Physics.Managers.PhysicsObjectData;
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
        double animationDuration = 8;


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationSpeed(0.01));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeCelestialBodiesPaused(true));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationWorldCentre(PhysicsObjectData.getInstance().getTitanObject()));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getRocketShipObject().getDrawableUI(), 0.25));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeDrawableUIScale(animationDuration, PhysicsObjectData.getInstance().getTitanObject().getDrawableUI(), 20));


        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScale(0.000001));

        LandRocketState.subscribeListenerToEnterEvent(new ChangeSimulationScaleSize(10));
    }

    private void createOnExitLandRocketUIEvents()
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
}
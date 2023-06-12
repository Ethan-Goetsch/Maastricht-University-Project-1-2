package group9.project.States.Rocket;

import group9.project.Controllers.LandingController;
import group9.project.Data.Data;
import group9.project.Events.Event;
import group9.project.Events.IEventListener;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.States.IStateManager;
import group9.project.Utility.Coordinates.Coordinates;
import group9.project.Utility.Math.Vector3;

public class LandRocketState extends RocketState
{
    private LandingController landingController;

    private Vector3 landingCoordinates;

    private static Event onEnterLandStateEvent = new Event();

    private static Event onExitLandStateEvent = new Event();


    public LandRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, LandingController newLandingController, Vector3 newLandingCoordinates)
    {
        super(newStateManager, newRocketShip);

        landingController = newLandingController;

        landingCoordinates = newLandingCoordinates;
    }

    @Override
    public boolean canEnterState()
    {
        return true;
    }

    @Override
    public boolean canExitState()
    {
        Vector3 relativeToLandingCoordiantesPosition = Coordinates.RelativeTo(rocketShip.getPosition(), landingCoordinates);

        return Data.isLanded(relativeToLandingCoordiantesPosition.getX(), relativeToLandingCoordiantesPosition.getY(), rocketShip.getTorque(), rocketShip.getVelocity().getX(), rocketShip.getVelocity().getY(), rocketShip.getTorqueVelocity());
    }

    @Override
    public void onStateEnter()
    {
        onEnterLandStateEvent.raiseEvent();
        
        rocketShip.setVelocity(new Vector3());

        rocketShip.setAcceleration(new Vector3());
    }

    @Override
    public void onStateExit()
    {
        onExitLandStateEvent.raiseEvent();
    }

    @Override
    public void update()
    {
        landingController.updateLandPath(landingCoordinates);


        double mainThrusterAcceleration = landingController.getMainThrusterAcceleration();

        double sideThrusterTorque = landingController.getSideThrusterTorque();
        

        Vector3[] positionAndVelocity = PhysicsEngine.getInstance().calculateMotion(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getTorque(), mainThrusterAcceleration, sideThrusterTorque, PhysicsSettings.getStepSize());

        Double[] torqueAndVelocity = PhysicsEngine.getInstance().calculateRotation(rocketShip.getTorque(), rocketShip.getTorqueVelocity(), sideThrusterTorque, PhysicsSettings.getStepSize());
        

        rocketShip.setPosition(positionAndVelocity[0]);

        rocketShip.setVelocity(positionAndVelocity[1]);


        rocketShip.setTorque(torqueAndVelocity[0]);

        rocketShip.setTorqueVelocity(torqueAndVelocity[1]);
    }

    @Override
    public String getDescription()
    {
        return "Landing State";
    }

    public static void subscribeListenerToEnterEvent(IEventListener listener)
    {
        onEnterLandStateEvent.subscribeListener(listener);  
    }

    public static void subscribeListenerToExitEvent(IEventListener listener)
    {
        onExitLandStateEvent.subscribeListener(listener);
    }
}
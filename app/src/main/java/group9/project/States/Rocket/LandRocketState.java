package group9.project.States.Rocket;

import group9.project.Controllers.LandingController;
import group9.project.Data.Data;
import group9.project.Events.Event;
import group9.project.Events.IEventListener;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Solvers.DiscreteSolver;
import group9.project.States.IStateManager;
import group9.project.Utility.Coordinates.Coordinates;
import group9.project.Utility.Math.Vector3;

public class LandRocketState extends RocketState
{
    private LandingController landingController;

    private DiscreteSolver discreteSolver;

    private Vector3 landingPosition;

    private static Event onEnterLandStateEvent = new Event();

    private static Event onExitLandStateEvent = new Event();


    public LandRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, LandingController newLandingController, Vector3 newLandingPosition)
    {
        super(newStateManager, newRocketShip);

        landingController = newLandingController;

        discreteSolver = new DiscreteSolver();

        landingPosition = newLandingPosition;
    }

    @Override
    public boolean canEnterState()
    {
        return true;
    }

    @Override
    public boolean canExitState()
    {
        Vector3 relativeToLandingCoordiantesPosition = Coordinates.RelativeTo(rocketShip.getPosition(), landingPosition);

        return Data.isLanded(relativeToLandingCoordiantesPosition.getX(), relativeToLandingCoordiantesPosition.getY(), rocketShip.getRotation(), rocketShip.getVelocity().getX(), rocketShip.getVelocity().getY(), rocketShip.getRotationVelocity());
    }

    @Override
    public void onStateEnter()
    {
        onEnterLandStateEvent.raiseEvent();

        rocketShip.setPosition(new Vector3(landingPosition.getX() - 500, landingPosition.getY() - 1000, 0));
        
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
        landingController.updateLandPath(landingPosition, PhysicsSettings.getStepSize());


        double thrusterRotation = landingController.getThrusterRotation();

        double thrusterAcceleration = landingController.getThrusterAcceleration();
        

        double[] torqueAndVelocity = discreteSolver.calculateRotation(rocketShip.getRotation(), thrusterRotation, PhysicsSettings.getStepSize());

        Vector3[] positionAndVelocity = discreteSolver.calculateMotion(rocketShip.getPosition(), rocketShip.getRotation(), thrusterAcceleration, PhysicsSettings.getStepSize());


        rocketShip.setRotation(torqueAndVelocity[0]);

        rocketShip.setRotationVelocity(torqueAndVelocity[1]);


        rocketShip.setPosition(new Vector3(positionAndVelocity[0].getX(), positionAndVelocity[0].getY(), landingPosition.getZ()));

        rocketShip.setVelocity(positionAndVelocity[1]);
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
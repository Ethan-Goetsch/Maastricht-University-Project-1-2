package group9.project.States.Rocket;

import group9.project.Controllers.LandingController;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.IStateManager;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class LandRocketState extends RocketState
{
    private ITargetable target;

    private LandingController landingController;

    public LandRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, ITargetable newTarget, LandingController newLandingController)
    {
        super(newStateManager, newRocketShip);
        
        target = newTarget;

        landingController = newLandingController;
    }

    @Override
    public boolean canEnterState()
    {
        return true;
    }

    @Override
    public boolean canExitState()
    {
        return false;
    }

    @Override
    public void onStateEnter()
    {
        rocketShip.setVelocity(new Vector3());

        rocketShip.setAcceleration(new Vector3());
    }

    @Override
    public void onStateExit()
    {

    }

    @Override
    public void update()
    {
        double mainThrusterAcceleration = landingController.getMainThrusterAcceleration();

        double sideThrusterTorque = landingController.getSideThrusterTorque();

        Vector3 newVelocity = PhysicsEngine.getInstance().calculateMotion(mainThrusterAcceleration, sideThrusterTorque);

        rocketShip.setVelocity(newVelocity);
    }

    @Override
    public String getDescription()
    {
        return "Landing " + target.getDescription() + " State";
    }
}
package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.IStateManager;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class LandRocketState extends RocketState
{
    private ITargetable target;

    public LandRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, ITargetable newTarget)
    {
        super(newStateManager, newRocketShip);
        
        target = newTarget;
    }

    @Override
    public boolean canEnterState()
    {
        return true;
    }

    @Override
    public boolean canExitState()
    {
        return true;
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
        double u = 0;

        double theta = 0;

        double g = 0;

        double xAcceleration = u * Math.sin(theta);

        double yAcceleration = u * Math.cos(theta) - g;

        Vector3 newVelocity = new Vector3(xAcceleration, yAcceleration, rocketShip.getVelocity().getZ());

        rocketShip.setVelocity(newVelocity);
    }

    @Override
    public String getDescription()
    {
        return "Landing " + target.getDescription() + " State";
    }
}
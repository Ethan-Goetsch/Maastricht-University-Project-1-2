package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.IStateManager;
import group9.project.States.State;

public abstract class RocketState extends State
{
    protected RocketShipObject rocketShip;

    public RocketState(IStateManager newStateManager, RocketShipObject newRocketShip)
    {
        super(newStateManager);

        rocketShip = newRocketShip;
    }

    public boolean canEnterState()
    {
        return true;
    }

    public boolean canExitState()
    {
        return true;
    }

    @Override
    public abstract void onStateEnter();

    @Override
    public abstract void onStateExit();

    @Override
    public abstract void update();

    @Override
    public abstract String getDescription();
}
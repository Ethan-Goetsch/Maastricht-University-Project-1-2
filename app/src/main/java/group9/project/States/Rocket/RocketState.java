package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.State;

public abstract class RocketState extends State
{
    protected RocketShipObject rocketShip;

    public RocketState(RocketShipObject newRocketShip)
    {
        super(newRocketShip);

        rocketShip = newRocketShip;
    }

    @Override
    public abstract void onStateEnter();

    @Override
    public abstract void onStateExit();

    @Override
    public abstract void update();
}
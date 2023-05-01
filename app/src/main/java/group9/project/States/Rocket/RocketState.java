package group9.project.States.Rocket;

import group9.project.Hill_Climbing.FuelOptimizer;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.State;
import group9.project.Trajectory_Guidance.TrajectoryDirection;

public abstract class RocketState extends State
{
    protected RocketShipObject rocketShip;

    protected TrajectoryDirection trajectoryDirection;

    protected FuelOptimizer fuelOptimizer;

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
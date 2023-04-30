package group9.project.States.Rocket;

import java.util.List;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.IState;
import group9.project.States.IStateTransitionable;

public abstract class RocketState implements IState, IStateTransitionable
{
    protected RocketShipObject rocketShip;

    protected List<RocketState> neighbourStates;

    public RocketState(RocketShipObject newRocketShip, List<RocketState> newNeighbourStates)
    {
        rocketShip = newRocketShip;

        neighbourStates = newNeighbourStates;
    }

    @Override
    public abstract void onStateEnter();

    @Override
    public abstract void onStateExit();

    @Override
    public abstract boolean canTransition();

    @Override
    public void checkStateTransitions()
    {
        for (RocketState rocketState : neighbourStates)
        {
            if (rocketState.canTransition())
            {
                rocketShip.transitionToState(rocketState);

                return;
            }    
        }
    }

    public abstract void tick();
}
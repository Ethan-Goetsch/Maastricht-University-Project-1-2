package group9.project.States;

import java.util.ArrayList;
import java.util.List;

import group9.project.States.Transitions.StateTransition;
import group9.project.Utility.Interfaces.IUpdateable;

public abstract class State implements IState, IUpdateable, IStateTransitionable
{
    private IStateManager stateManager;

    protected List<StateTransition> stateTransitions;

    public State(IStateManager newStateManager)
    {
        stateManager = newStateManager;

        stateTransitions = new ArrayList<>();
    }

    public void addStateTransition(StateTransition rocketState)
    {
        stateTransitions.add(rocketState);
    }

    public void addStateTransition(List<StateTransition> rocketStates)
    {
        stateTransitions.addAll(rocketStates);
    }
    
    @Override
    public abstract void onStateEnter();

    @Override
    public abstract void onStateExit();

    @Override
    public abstract void update();

    @Override
    public void checkStateTransitions()
    {
        for (StateTransition stateTransition : stateTransitions)
        {
            if (stateTransition.canTransition())
            {
                stateManager.transitionToState(stateTransition.getState());

                return;
            }    
        }
    }
}
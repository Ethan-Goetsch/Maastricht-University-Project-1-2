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

    /**
     * Adds a State Transition to the State
     * 
     * @param newStateTransitions the state transition to add to the State's State Transition
     */
    public void addStateTransition(StateTransition newStateTransitions)
    {
        stateTransitions.add(newStateTransitions);
    }

    /**
     * Adds a list of State Transition to the State
     * 
     * @param newStateTransition the state transitions to add
     */
    public void addStateTransition(List<StateTransition> newStateTransition)
    {
        stateTransitions.addAll(newStateTransition);
    }
    
    @Override
    public abstract void onStateEnter();

    @Override
    public abstract void onStateExit();

    @Override
    public abstract void update();

    /**
     * Iterates over each State Transition and checks if it can transition. If it can transition it will tell the State's Manager to transition to that state
     */
    @Override
    public void checkStateTransitions()
    {
        for (StateTransition stateTransition : stateTransitions)
        {
            if (stateTransition.canTransition())
            {
                stateManager.transitionToState(stateTransition.getStateToTransitionTo());

                return;
            }    
        }
    }

    @Override
    public abstract String getDescription();
}
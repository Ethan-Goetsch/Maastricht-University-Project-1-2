package group9.project.States.Transitions;

import group9.project.States.IState;

public class StateTransition
{
    private IState state;

    private ITransitionProperty transitionProperty;

    public StateTransition(IState newState, ITransitionProperty newTransitionProperty)
    {
        state = newState;

        transitionProperty = newTransitionProperty;
    }

    public IState getState()
    {
        return state;
    }

    public boolean canTransition()
    {
        return transitionProperty.canTransition();
    }
}
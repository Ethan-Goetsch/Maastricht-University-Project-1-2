package group9.project.States.Transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group9.project.States.IState;

public class StateTransition
{
    private IState stateToTransitionTo;

    private List<ITransitionProperty> transitionProperties;

    public IState getStateToTransitionTo()
    {
        return stateToTransitionTo;
    }

    public StateTransition(IState newStateToTransitionTo)
    {
        stateToTransitionTo = newStateToTransitionTo;

        transitionProperties = new ArrayList<>();
    }

    public StateTransition(IState newStateToTransitionTo, ITransitionProperty newTransitionProperty)
    {
        stateToTransitionTo = newStateToTransitionTo;

        transitionProperties = Arrays.asList(newTransitionProperty);
    }

    public StateTransition(IState newStateToTransitionTo, List<ITransitionProperty> newTransitionProperties)
    {
        stateToTransitionTo = newStateToTransitionTo;

        transitionProperties = newTransitionProperties;
    }

    /**
     * Adds a transition property to the State Transition
     * 
     * @param newTransitionProperty the transition property to add
     */
    public void addTransitionProperty(ITransitionProperty newTransitionProperty)
    {
        transitionProperties.add(newTransitionProperty);
    }

    /**
     * @return true if all of its transition properties can transition
     */
    public boolean canTransition()
    {
        for (ITransitionProperty transitionProperty : transitionProperties)
        {
            if (!transitionProperty.canTransition())
            {
                return false;
            }    
        }

        return true;
    }
}
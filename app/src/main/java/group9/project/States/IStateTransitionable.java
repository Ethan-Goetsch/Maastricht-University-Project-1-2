package group9.project.States;

public interface IStateTransitionable
{
    public boolean canTransition();

    public void checkStateTransitions();
}
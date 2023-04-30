package group9.project.States;

public interface IStateManager
{
    public void transitionToState(IState state);

    public void tickState();
}
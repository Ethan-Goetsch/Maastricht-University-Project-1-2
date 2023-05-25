package group9.project.States;

import group9.project.Utility.Interfaces.IDescribable;

public interface IState extends IDescribable
{
    public void onStateEnter();

    public void onStateExit();

    public String getDescription();
}
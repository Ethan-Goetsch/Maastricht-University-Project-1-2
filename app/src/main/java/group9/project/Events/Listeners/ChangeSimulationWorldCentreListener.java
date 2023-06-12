package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Interfaces.ITargetable;

public class ChangeSimulationWorldCentreListener implements IEventListener
{
    private ITargetable worldCentreTarget;

    public ChangeSimulationWorldCentreListener(ITargetable newWorldCentreTarget)
    {
        worldCentreTarget = newWorldCentreTarget;
    }

    @Override
    public void onEvent()
    {
        ScaleConverter.setWorldCentreTarget(worldCentreTarget);
    } 
}
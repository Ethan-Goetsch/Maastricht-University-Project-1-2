package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.UI.ScaleConverter;

public class ChangeSimulationScale implements IEventListener
{
    private double simulationScale;

    public ChangeSimulationScale(double newSimulationScale)
    {
        simulationScale = newSimulationScale;
    }

    @Override
    public void onEvent()
    {
        ScaleConverter.setScaleFactor(simulationScale);
    } 
}
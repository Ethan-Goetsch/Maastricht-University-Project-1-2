package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.UI.ScaleConverter;

public class ChangeSimulationScaleListener implements IEventListener
{
    private double simulationScale;

    public ChangeSimulationScaleListener(double newSimulationScale)
    {
        simulationScale = newSimulationScale;
    }

    @Override
    public void onEvent()
    {
        ScaleConverter.setScaleFactor(simulationScale);
    } 
}
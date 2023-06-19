package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.UI.ScaleConverter;

public class ChangeSimulationScaleSize implements IEventListener
{
    private double simulationScaleSize;

    public ChangeSimulationScaleSize(double newSimulationScaleSize)
    {
        simulationScaleSize = newSimulationScaleSize;
    }

    @Override
    public void onEvent()
    {
        ScaleConverter.setScaleSize(simulationScaleSize);
    }    
}
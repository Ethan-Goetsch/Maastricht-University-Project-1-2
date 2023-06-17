package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.Settings.SimulationSettings;

public class ChangeCelestialBodiesPaused implements IEventListener
{
    private boolean isPaused;

    public ChangeCelestialBodiesPaused(boolean newIsPaused)
    {
        isPaused = newIsPaused;
    }

    @Override
    public void onEvent()
    {
        SimulationSettings.setCelestialBodiesPaused(isPaused);
    }   
}
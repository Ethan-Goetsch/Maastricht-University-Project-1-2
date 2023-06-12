package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.Settings.SimulationSettings;

public class ChangeSimulationSpeedListener implements IEventListener
{
    private double simulationSpeed;

    public ChangeSimulationSpeedListener(double newSimulationSpeed)
    {
        simulationSpeed = newSimulationSpeed;
    }

    @Override
    public void onEvent()
    {
        SimulationSettings.setSimulationSpeed(simulationSpeed);
    } 
}
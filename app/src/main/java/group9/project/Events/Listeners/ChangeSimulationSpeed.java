package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.Settings.SimulationSettings;

public class ChangeSimulationSpeed implements IEventListener
{
    private double simulationSpeed;

    public ChangeSimulationSpeed(double newSimulationSpeed)
    {
        simulationSpeed = newSimulationSpeed;
    }

    @Override
    public void onEvent()
    {
        SimulationSettings.setSimulationSpeed(simulationSpeed);
    } 
}
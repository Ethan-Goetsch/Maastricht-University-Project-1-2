package group9.project.Events;

import group9.project.MissionControl;
import group9.project.Optimization.Optimization;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.Drawable.DrawableManager;

public class ResetSimulationListener implements IEventListener
{
    @Override
    public void onEvent()
    {
        if (!SimulationSettings.getDEVELOPMENT_MODE())
        {
            return;
        }

        restartSimulation();
    }

    private void restartSimulation()
    {
        Optimization.getInstance().reset();

        DrawableManager.getInstance().reset();

        PhysicsEngine.getInstance().reset();

        SimulationSettings.updateSimulationTime(-SimulationSettings.getSimulationTime());

        MissionControl.getInstance().restart();

        SimulationSettings.unpauseSimulation();
    }
}
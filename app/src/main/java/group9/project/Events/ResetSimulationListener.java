package group9.project.Events;

import group9.project.MissionControl;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.Drawable.DrawableManager;

public class ResetSimulationListener implements IEventListener
{
    private static final boolean DEBUG = true;

    @Override
    public void onEvent()
    {
        if (!DEBUG)
        {
            return;
        }

        restartSimulation();
    }

    private void restartSimulation()
    {
        PhysicsEngine.getInstance().getPhysicsObjectsToUpdate().clear();

        DrawableManager.getInstance().clear();

        SimulationSettings.updateSimulationTime(-SimulationSettings.getSimulationTime());

        SimulationSettings.playSimulation();

        MissionControl.getInstance().restart();
    }
}
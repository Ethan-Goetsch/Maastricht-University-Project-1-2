package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.Managers.SystemsManager;
import group9.project.Managers.TimelineManager;
import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import group9.project.Optimization.OptimizationDevelopmentMode;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Settings.SimulationSettings;
import group9.project.UI.Drawable.DrawableManager;

public class ResetSimulation implements IEventListener
{    
    /**
    * Resets the Simulation if the Simulation is in development mode
    */
    @Override
    public void onEvent()
    {
        if (SimulationSettings.getOptimizationDevelopmentMode() == OptimizationDevelopmentMode.None)
        {
            return;
        }

        restartSimulation();
    }

    private void restartSimulation()
    {
        SimulationSettings.pauseSimulation();
        

        if (LaunchToTitanOptimization.getInstance().getIsOptimizationDevelopmentMode())
        {
            LaunchToTitanOptimization.getInstance().reset();
        }
        
        if (LaunchToEarthOptimization.getInstance().getIsOptimizationDevelopmentMode())
        {
            LaunchToEarthOptimization.getInstance().reset();
        }

        
        DrawableManager.getInstance().reset();

        PhysicsEngine.getInstance().reset();


        SystemsManager.getInstance().reset();

        TimelineManager.getInstance().reset();


        SimulationSettings.updateSimulationTime(-SimulationSettings.getSimulationTime());

        SimulationSettings.playSimulation();
    }
}
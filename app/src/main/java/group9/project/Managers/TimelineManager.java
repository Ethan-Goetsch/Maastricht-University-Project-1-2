package group9.project.Managers;

import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import group9.project.Physics.Managers.PhysicsEngine;


public class TimelineManager {
    
    private boolean paused;
 
    //#region Singleton
    private static TimelineManager instance;
    
    public static TimelineManager getInstance()
    {
        if (instance == null)
        {
            instance = new TimelineManager();
        }
        return instance;
    }
    //#endregion
    
    private TimelineManager()
    {
        paused = true;
    }
    
    public void update()
    {
        if (paused) return;
        
        PhysicsEngine.getInstance().update();

        LaunchToTitanOptimization.getInstance().update();

        LaunchToEarthOptimization.getInstance().update();
    }
    
    public void playTimeline()
    {
        paused = false;
    }
    
    public void pauseTimeline()
    {
        paused = true;
    }
    
}

package group9.project.Managers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Managers.PhysicsVisualizer;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Interfaces.IResetable;
import group9.project.Utility.Interfaces.IStartable;

public class TimelineManager implements IStartable, IResetable
{
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

    private Timeline loopTimeline;

    private TimelineManager()
    {

    }
    
    private void createTimeline()
    {
        loopTimeline = new Timeline(new KeyFrame(Duration.seconds(PhysicsSettings.getUniverseTickTime()), x -> updateLoop()));

        loopTimeline.setCycleCount(Animation.INDEFINITE);
    }

    private void updateLoop()
    {
        PhysicsEngine.getInstance().update();

        LaunchToTitanOptimization.getInstance().update();

        LaunchToEarthOptimization.getInstance().update();

        PhysicsVisualizer.getInstance().update();
    }

    /**
     * Plays the System Manager's Timeline
     */
    public void playTimeline()
    {
        loopTimeline.play();
    }

    /**
     * Pauses the System Manager's Timeline
     */
    public void pauseTimeline()
    {
        loopTimeline.pause();
    }

    /**
     * Stops the System Manager's Timeline
     */
    public void stopTimeline()
    {
        loopTimeline.stop();
    }

    /**
     * Starts the System Manager
     */
    @Override
    public void start()
    {
        createTimeline();
    }

    /**
     * Resets the System Manager's Timeline
     */
    @Override
    public void reset()
    {
        stopTimeline();

        createTimeline();

        playTimeline();
    }
}
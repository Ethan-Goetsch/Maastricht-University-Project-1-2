package group9.project.Animations;

import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Interfaces.ITimedAction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Animation
{
    private Timeline actionTimeline;


    private double animationDuration;

    private double animationSpeed;

    private double currentTime;
    

    private ITimedAction timedAction;

    public Animation(double newAnimationDuration, ITimedAction newTimedAction)
    {
        animationDuration = newAnimationDuration;

        animationSpeed = PhysicsSettings.getUniverseTickTime();

        timedAction = newTimedAction;
    }

    public Animation(double newAnimationDuration, ITimedAction newTimedAction, double newAnimationSpeed)
    {
        animationDuration = newAnimationDuration;

        animationSpeed = newAnimationSpeed;

        timedAction = newTimedAction;
    }

    public void play()
    {
        actionTimeline = new Timeline(new KeyFrame(Duration.seconds(animationSpeed), x -> tickAnimation()));

        actionTimeline.setCycleCount(Timeline.INDEFINITE);

        actionTimeline.play();
    }   

    private void tickAnimation()
    {
        if (currentTime >= animationDuration)
        {
            actionTimeline.stop();
        }

        currentTime += animationSpeed;

        timedAction.onAction(currentTime / animationDuration);
    }
}
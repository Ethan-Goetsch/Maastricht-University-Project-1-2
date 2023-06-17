package group9.project.Events.Listeners;

import group9.project.Animations.Animation;
import group9.project.Events.IEventListener;

public abstract class ChangeAnimation implements IEventListener
{
    private double animationDuration;

    public ChangeAnimation(double newAnimationDuration)
    {
        animationDuration = newAnimationDuration;
    }

    @Override
    public void onEvent()
    {
        Animation animation = new Animation(animationDuration, x -> eventAction(x));

        animation.play();
    }

    protected abstract void eventAction(double time);
}
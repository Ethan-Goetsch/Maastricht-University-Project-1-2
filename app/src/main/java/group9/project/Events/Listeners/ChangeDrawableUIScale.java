package group9.project.Events.Listeners;

import group9.project.Interpolation.Interpolation;
import group9.project.UI.Drawable.DrawableUI;

public class ChangeDrawableUIScale extends ChangeAnimation
{
    private DrawableUI drawableUI;

    private double drawableUIScale;

    public ChangeDrawableUIScale(double newAnimationDuration, DrawableUI newDrawableUI, double newDrawableUIScale)
    {
        super(newAnimationDuration);

        drawableUI = newDrawableUI;
        
        drawableUIScale = newDrawableUIScale;
    }

    @Override
    protected void eventAction(double time)
    {
        drawableUI.setScale(Interpolation.SmoothStep.apply(drawableUI.getScale(), drawableUIScale, time));
    } 
}
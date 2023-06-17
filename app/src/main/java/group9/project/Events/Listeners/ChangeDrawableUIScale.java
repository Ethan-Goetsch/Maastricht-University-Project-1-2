package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.UI.Drawable.DrawableUI;

public class ChangeDrawableUIScale implements IEventListener
{
    private DrawableUI drawableUI;

    private double drawableUIScale;

    public ChangeDrawableUIScale(DrawableUI newDrawableUI, double newDrawableUIScale)
    {
        drawableUI = newDrawableUI;
        
        drawableUIScale = newDrawableUIScale;
    }

    @Override
    public void onEvent()
    {
        drawableUI.setScale(drawableUIScale);
    } 
}
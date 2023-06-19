package group9.project.Events.Listeners;

import group9.project.Events.IEventListener;
import group9.project.Managers.TimelineManager;

public class PlaySimulation implements IEventListener
{
    /**
     * Plays the Timeline Manager's Timeline
     */
    @Override
    public void onEvent()
    {
        TimelineManager.getInstance().playTimeline();
    }
}
package group9.project.Events;

import java.util.ArrayList;
import java.util.List;

public class Event implements ISubscribable, IUnsubscribable
{
    private List<IEventListener> listeners = new ArrayList<>();

    /**
     * Raises the On Event method on each of the Event's listeners.
     * Raises them in descending order to prevent an Exception when a listeners On Event unsubscribes it from the Event
     */
    public void raiseEvent()
    {
        for (int i = listeners.size() - 1; i >= 0; i--)
        {
            listeners.get(i).onEvent();
        }
    }
    
    /**
     * Subscribes a listener to the Event
     * 
     * @param listener the listener to subscribe
     */
    @Override
    public void subscribeListener(IEventListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Unsubcribes a listener to the Event
     * 
     * @param listener the listener to unsubscribe
     */
    @Override
    public void unsubscribeListener(IEventListener listener)
    {
        listeners.remove(listener);
    }
}
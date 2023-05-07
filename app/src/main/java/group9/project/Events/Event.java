package group9.project.Events;

import java.util.ArrayList;
import java.util.List;

public class Event implements ISubscribable, IUnsubscribable
{
    private List<IEventListener> listeners = new ArrayList<>();

    public void raiseEvent()
    {
        for (int i = listeners.size() - 1; i >= 0; i--)
        {
            listeners.get(i).onEvent();
        }
    }

    @Override public void subscribeListener(IEventListener listener)
    {
        listeners.add(listener);
    }

    @Override public void unsubscribeListener(IEventListener listener)
    {
        listeners.remove(listener);
    }
}
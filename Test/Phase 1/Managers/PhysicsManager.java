package Managers;

import java.util.ArrayList;

import Interfaces.*;

public abstract class PhysicsManager<E extends PhysicsItem> implements IStartable, IUpdateable
{
    protected ArrayList<E> physicsItems;

    public void addPhysicsItem(E physicsItem)
    {
        if (!physicsItems.contains(physicsItem))
        {
            physicsItems.add(physicsItem);

            physicsItem.start();
        }
    }

    @Override
    public abstract void start();

    @Override
    public abstract void fixedUpdate();
}
package Managers;

import java.util.ArrayList;
import Components.PhysicsComponent;

public class PhysicsVisualizer extends PhysicsManager<PhysicsComponent>
{
    private static PhysicsVisualizer instance;

    public static PhysicsVisualizer getInstance() 
    {
        if (instance == null)
        {
            instance = new PhysicsVisualizer();
        }

        return instance;
    }

    public PhysicsVisualizer()
    {
        instance = this;
    }

    @Override
    public void start()
    {
        physicsItems = new ArrayList<>();
    }

    @Override
    public void fixedUpdate()
    {
        for (PhysicsComponent physicsComponent : physicsItems)
        {
            physicsComponent.fixedUpdate();    
        }
    }
}
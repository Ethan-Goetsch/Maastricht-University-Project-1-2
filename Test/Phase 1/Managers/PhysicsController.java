package Managers;

import java.util.ArrayList;
import Objects.PhysicsObject;

public class PhysicsController extends PhysicsManager<PhysicsObject>
{
    private static PhysicsController instance;

    public static PhysicsController getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsController();
        }

        return instance;
    }

    public PhysicsController()
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
        for (PhysicsObject physicsObject : physicsItems)
        {
            physicsObject.fixedUpdate();
        }
    }
}
package group9.project;

import java.util.ArrayList;

public class PhysicsEngine
{
    public static final double STEP_TIME = 0.1;

    private static ArrayList<IUpdateable> physicsObjectsToUpdate = new ArrayList<>();

    public PhysicsEngine()
    {
        physicsObjectsToUpdate = new ArrayList<>();
    }

    public static void addPhysicsObjectToUpdate(IUpdateable updateable)
    {
        if (!physicsObjectsToUpdate.contains(updateable))
        {
            physicsObjectsToUpdate.add(updateable);
        }
    }

    public static void updatePhysicsObjects()
    {
        for (IUpdateable physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }
}
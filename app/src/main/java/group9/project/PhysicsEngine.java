package group9.project;

import java.util.ArrayList;

public class PhysicsEngine
{
    public static final double STEP_TIME = 0.1;

    private static ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public PhysicsEngine()
    {
        physicsObjectsToUpdate = new ArrayList<>();
    }

    public static void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        if (!physicsObjectsToUpdate.contains(physicsObject))
        {
            physicsObjectsToUpdate.add(physicsObject);
        }
    }

    public static void update() {
        // calculate and apply forces to all physics objects
    }

    public static void updatePhysicsObjects()
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }
}
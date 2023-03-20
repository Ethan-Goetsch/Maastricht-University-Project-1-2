public class PhysicsEngine
{
    public static final double STEP_TIME = 0.1;

    private static ArrayList<IUpdateable> physicsObjectsToUpdate;

    public static void addPhysicsObjectToUpdate(IUpdateable updateable)
    {
        if (!physicsObjectsToUpdate.contains(updateable))
        {
            physicsObjectsToUpdate.add(updateable);
        }
    }

    public static void createTimer()
    {
        //timer goes here
    }
}
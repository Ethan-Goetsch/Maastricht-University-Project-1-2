package group9.project;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class PhysicsEngine implements IStartable, IUpdateable
{
    //#region Singleton
    private static PhysicsEngine instance;

    public static PhysicsEngine getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsEngine();
        }

        return instance;
    }
    //#endregion

    public static final double STEP_TIME = 0.1;

    public static final double GRAVITY = 7.6743e-20;

    private ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public ArrayList<PhysicsObject> getPhysicsObjectsToUpdate()
    {
        return physicsObjectsToUpdate;
    }

    public PhysicsEngine()
    {
        physicsObjectsToUpdate = new ArrayList<>();
    }

    public void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        if (!physicsObjectsToUpdate.contains(physicsObject))
        {
            physicsObjectsToUpdate.add(physicsObject);

            physicsObject.start();
        }
    }

    @Override
    public void start()
    {
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(450, 450, 0), new Vector3(1, 1, 1), new Vector3(25, -25, 50), 50, Color.RED);

        RocketShipObject rocketShipObject = new RocketShipObject(new Vector3(0, 0, 0), new Vector3(), new Vector3(), 50, 75, Color.BLUE);
    }

    @Override
    public void update()
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }

        System.out.println("test");
    }
}
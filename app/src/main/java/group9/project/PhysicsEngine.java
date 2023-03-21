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
        }
    }

     
    @Override
    public void start()
    {
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(450, 450, 0), new Vector3(1, -2, 1), new Vector3(1, 1, 1), 50, "Sun", 50, Color.RED);

        RocketShipObject rocketShipObject = new RocketShipObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(), 0, "Rocket", 50, 75, Color.BLUE);
    }

    @Override
    public void update()
    {
        updateForces();

        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }
    
    private void updateForces()
    {
        // calculate and apply forces to all physics objects

        // gravity:
        for (PhysicsObject physicsBodyOne : getPhysicsObjectsToUpdate())
        {
            for (PhysicsObject physicsBodyTwo : getPhysicsObjectsToUpdate()) 
            {
                if (physicsBodyOne.equals(physicsBodyTwo)) continue; 

                    // Calculate the distance vector between the two objects
                    System.out.println(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()));

                    Vector3 r = Converter.scaleToSolarSystem(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()));

                    System.out.println("distance: " + r.getMagnitude());

                    // Calculate the magnitude of the gravitational force using the universal law of gravitation
                    double magF = GRAVITY * physicsBodyOne.getMass() * physicsBodyTwo.getMass() / Math.pow(r.getMagnitude(), 2);

                    // Calculate the direction of the gravitational force
                    Vector3 dirF = r.normalize();

                    // Calculate the gravitational force vector
                    Vector3 F = dirF.multiplyBy(magF);

                    physicsBodyOne.applyForce(F);
            }
        }
    }
}
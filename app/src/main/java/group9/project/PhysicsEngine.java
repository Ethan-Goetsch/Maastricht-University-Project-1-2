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

    public static void updateForces() {
        // calculate and apply forces to all physics objects

        // gravity:

        for (PhysicsObject body : physicsObjectsToUpdate) {

            for (PhysicsObject body2 : physicsObjectsToUpdate) {
                if (body.equals(body2)) continue; 

                    // Calculate the distance vector between the two objects
                    System.out.println(body2.getPosition().subtract(body.getPosition()));
                    Vector3 r = Converter.scaleToSolarSystem(body2.getPosition().subtract(body.getPosition()));
                    System.out.println("distance: " + r.getMagnitude());

                    // Calculate the magnitude of the gravitational force using the universal law of gravitation
                    double magF = GRAVITY * body.getMass() * body2.getMass() / Math.pow(r.getMagnitude(), 2);

                    // Calculate the direction of the gravitational force
                    Vector3 dirF = r.normalize();

                    // Calculate the gravitational force vector
                    Vector3 F = dirF.multiplyBy(magF);

                    body.applyForce(F);
                
            }
        }
    }

    @Override
    public void update()
    {
        updateForces();
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update(10); // seconds in a day
        }

        //System.out.println("test");
    }
}
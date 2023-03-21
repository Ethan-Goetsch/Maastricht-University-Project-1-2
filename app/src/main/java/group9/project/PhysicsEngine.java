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

    public static final double STEP_TIME = 0.001;

    //public static final double GRAVITY = 6.6743e-20;
    public static final double GRAVITY = 6.6743e-15;



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
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1.99e30, "Sun", 10, Color.RED);

        CelestialBodyObject saturnObject = new CelestialBodyObject(new Vector3(1.25e9, -7.60e8, -3.67e7), new Vector3(4.47, 8.24, -3.21e-1), new Vector3(), 5.68e26, "Saturn", 8, Color.BLUE);

        //CelestialBodyObject earthObject = new CelestialBodyObject(new Vector3(-1.48e8, -2.78e7, 3.37e4), new Vector3(5.05, -2.94, 1.71), new Vector3(), 5.97e24, "Earth", 5, Color.GREEN);

        //RocketShipObject rocketShipObject = new RocketShipObject(Converter.scaleToScreen(new Vector3(1.25e9, -7.61e8, -3.63e7)), new Vector3(0, 0, 0), new Vector3(), 50000, "Rocket", 50, 75, Color.BLUE);
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
                    //System.out.println("CALCULATING FORCE EXERTED ON " + physicsBodyOne.name + " BY " + physicsBodyTwo.name);

                    if (physicsBodyOne.getPosition().getX()==0 || physicsBodyOne.getPosition().getY()==0) {
                        //System.out.println("oh no");
                    }
                    // Calculate the distance vector between the two objects
                     
                    System.out.println("\tposition1: " + physicsBodyTwo.getPosition());
                    //System.out.println("\tposition2: " + physicsBodyOne.getPosition());
                    /* 
                    System.out.println(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()));
                    System.out.println(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()).getMagnitude());
                    System.out.println("\tdistance (screen): " + physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()).getMagnitude());
                    */

                    Vector3 r = physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition());
                    //System.out.println("\tdistance (solar): " + Converter.scaleToSolarSystem(r.getMagnitude()));

                    // Calculate the magnitude of the gravitational force using the universal law of gravitation
                    double magF = GRAVITY * physicsBodyOne.getMass() * physicsBodyTwo.getMass() / Math.pow(r.getMagnitude(), 2);
                    System.out.println("\tmagnitude of force: " + magF);

                    // Calculate the direction of the gravitational force
                    Vector3 dirF = r.normalize();
                    System.out.println("\tdirection of force: " + dirF);

                    // Calculate the gravitational force vector
                    Vector3 F = dirF.multiplyBy(magF);
                    //System.out.println("\tgravitational force vector: " + F);

                    physicsBodyOne.applyForce(F);
            }
        }
    }
}
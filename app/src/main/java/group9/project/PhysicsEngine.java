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


    //public static final double GRAVITY = 6.6743015e-11;
    public static final double GRAVITY = 6.6743015e-16;

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
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1.99E+30, "Sun", Color.RED);

        CelestialBodyObject mercuryObject = new CelestialBodyObject(new Vector3(7.83E+06, 4.49E+07, 2.87E+06), new Vector3(-5.75E+01, 1.15E+01,	6.22E+00), 3.30E+23, "Mercury", Color.GREEN);

        CelestialBodyObject venusObject = new CelestialBodyObject(new Vector3(-2.82E+07, 1.04E+08, 3.01E+06), new Vector3(-3.40E+01, -8.97E+00, 1.84E+00), 4.87E+24, "Venus", Color.PURPLE);

        CelestialBodyObject earthObject = new CelestialBodyObject(new Vector3(-1.48E+08, -2.78E+07, 3.37E+04), new Vector3(5.05E+00, -2.94E+01, 1.71E-03), 5.97E+24, "Earth", Color.BLUE);

        CelestialBodyObject moonObject = new CelestialBodyObject(new Vector3(-1.48E+08, -2.75E+07, 7.02E+04), new Vector3(4.34E+00, -3.00E+01, -1.16E-02), 7.35E+22, "Moon", Color.GRAY);

        CelestialBodyObject marsObject = new CelestialBodyObject(new Vector3(-1.59E+08,	1.89E+08, 7.87E+06), new Vector3(-1.77E+01, -1.35E+01, 1.52E-01), 6.42E+23, "Mars", Color.ORANGE);

        CelestialBodyObject jupiterObject = new CelestialBodyObject(new Vector3(6.93E+08,	2.59E+08, -1.66E+07), new Vector3(-4.71E+00, 1.29E+01, 5.22E-02), 1.90E+27, "Jupiter", Color.PINK);

        CelestialBodyObject saturnObject = new CelestialBodyObject(new Vector3(1.25E+09, -7.60E+08, -3.67E+07), new Vector3(4.47E+00,	8.24E+00, -3.21E-01), 5.68E+26, "Saturn", Color.ROYALBLUE);

        CelestialBodyObject titanObject = new CelestialBodyObject(new Vector3(1.25E+09, -7.61E+08,	-3.63E+07), new Vector3(9.00E+00, 1.11E+01, -2.25E+00), 1.35E+23, "Titan", Color.GOLD);

        CelestialBodyObject neptuneObject = new CelestialBodyObject(new Vector3(4.45E+09, -3.98E+08, -9.45E+07), new Vector3(4.48E-01, 5.45E+00, -1.23E-01), 1.02E+26, "Neptune", Color.BLACK);

        CelestialBodyObject uranusObject = new CelestialBodyObject(new Vector3(1.96E+09, 2.19E+09, -1.72E+07), new Vector3(-5.13E+00,	4.22E+00, 8.21E-02), 8.68E+25, "Uranus", Color.CRIMSON);

        RocketShipObject rocketShipObject = new RocketShipObject(new Vector3(450, 450, 0), new Vector3(10, 1, 0), 50000, "Rocket", 10, 10, Color.BLUE);
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
        for (PhysicsObject physicsBodyOne : getPhysicsObjectsToUpdate())
        {
            Vector3 physicsBodyOneForce = new Vector3();

            for (PhysicsObject physicsBodyTwo : getPhysicsObjectsToUpdate()) 
            {
                if (physicsBodyOne.equals(physicsBodyTwo))
                {
                    continue;
                }

                Vector3 positionDifference = physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition());

                double distance = Mathematics.getDistance(physicsBodyOne.getPosition(), physicsBodyTwo.getPosition());

                distance = Math.pow(distance, 2);

                double forceMagnitude = physicsBodyOne.getMass() * physicsBodyTwo.getMass() / distance;
                Vector3 force = positionDifference.normalize().multiplyBy(forceMagnitude);
                /* 
                Vector3 force = positionDifference.divideBy(distance);
                Vector3 force = physicsBodyOne.getMass()*physicsBodyTwo.getMass()
                */

                force = force.multiplyBy(GRAVITY);

                physicsBodyOneForce = physicsBodyOneForce.add(force);

                //#region Old Force
                //     // Calculate the distance vector between the two objects
                //     System.out.println(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()));

                //     Vector3 r = ScaleConverter.scaleToSolarSystem(physicsBodyTwo.getPosition().subtract(physicsBodyOne.getPosition()));

                //     System.out.println("distance: " + r.getMagnitude());

                //     // Calculate the magnitude of the gravitational force using the universal law of gravitation
                //     double magF = GRAVITY * physicsBodyOne.getMass() * physicsBodyTwo.getMass() / Math.pow(r.getMagnitude(), 2);

                //     // Calculate the direction of the gravitational force
                //     Vector3 dirF = r.normalize();

                //     // Calculate the gravitational force vector
                //     Vector3 F = dirF.multiplyBy(magF);

                //     physicsBodyOne.setForce(F);
                // }
                //#endregion
            }

            physicsBodyOne.setForce(physicsBodyOneForce);

            physicsBodyOne.setAcceleration(physicsBodyOne.getForce().divideBy(physicsBodyOne.getMass()));
        }
    }
}
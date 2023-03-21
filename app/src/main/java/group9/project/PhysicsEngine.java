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

    public static final double STEP_TIME = 0.0001;

    private static final double increasedStepTime = 10000000;

    public static final double SIMULATION_SPEED = 10;

    private static final double GRAVITY = 6.6743E-20;

    private ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public ArrayList<PhysicsObject> getPhysicsObjectsToUpdate()
    {
        return physicsObjectsToUpdate;
    }

    public static double getSpedUpStepTime()
    {
        return STEP_TIME * increasedStepTime * SIMULATION_SPEED;
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
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1.99E+30, PhysicsObjectType.Sun, 5, Color.RED);

        CelestialBodyObject mercuryObject = new CelestialBodyObject(new Vector3(7833268.43923962, 44885949.3703908, 2867693.20054382), new Vector3(-57.4967480139828, 11.52095127176, 6.21695374334136), 3.30E+23, PhysicsObjectType.Mercury, 10, Color.GREEN);

        CelestialBodyObject venusObject = new CelestialBodyObject(new Vector3(-28216773.9426889, 103994008.541512, 3012326.64296788), new Vector3(-34.0236737066136, -8.96521274688838, 1.84061735279188), 4.87E+24, PhysicsObjectType.Venus, 7.5, Color.PURPLE);

        CelestialBodyObject earthObject = new CelestialBodyObject(new Vector3(-148186906.893642, -27823158.5715694, 33746.8987977113), new Vector3(5.05251577575409, -29.3926687625899, 0.00170974277401292), 5.97E+24, PhysicsObjectType.Earth, 7.5, Color.BLUE);

        CelestialBodyObject moonObject = new CelestialBodyObject(new Vector3(-148458048.395164, -27524868.1841142, 70233.6499287411), new Vector3(4.34032634654904, -30.0480834180741, -0.0116103535014229), 7.35E+22, PhysicsObjectType.Moon, 2.5, Color.GRAY);

        CelestialBodyObject marsObject = new CelestialBodyObject(new Vector3(-1.59E+08,	1.89E+08, 7.87E+06), new Vector3(-17.6954469224752, -13.4635253412947, 1.52E-01), 0.152331928200531, PhysicsObjectType.Mars, 5, Color.ORANGE);

        CelestialBodyObject jupiterObject = new CelestialBodyObject(new Vector3(692722875.928222,	258560760.813524, -16570817.7105996), new Vector3(-4.71443059866156, 12.8555096964427, 0.0522118126939208), 1.90E+27, PhysicsObjectType.Jupiter, 20, Color.PINK);

        CelestialBodyObject saturnObject = new CelestialBodyObject(new Vector3(1253801723.95465, -760453007.810989, -36697431.1565206), new Vector3(4.46781341335014,	8.23989540475628, -0.320745376969732), 5.68E+26, PhysicsObjectType.Saturn, 15, Color.ROYALBLUE);

        CelestialBodyObject titanObject = new CelestialBodyObject(new Vector3(1254501624.95946, -761340299.067828,	-36309613.8378104), new Vector3(8.99593229549645, 11.1085713608453, -2.25130986174761), 1.35E+23, PhysicsObjectType.Titan, 2.5, Color.GOLD);

        CelestialBodyObject neptuneObject = new CelestialBodyObject(new Vector3(4454487339.09447, -397895128.763904, -94464151.3421107), new Vector3(0.447991656952326, 5.44610697514907, -0.122638125365954), 1.02E+26, PhysicsObjectType.Neptune, 7.5, Color.BLACK);

        CelestialBodyObject uranusObject = new CelestialBodyObject(new Vector3(1958732435.99338, 2191808553.21893, -17235283.8321992), new Vector3(-5.12766216337626,	4.22055347264457, 0.0821190336403063), 8.68E+25, PhysicsObjectType.Uranus, 7.5, Color.CRIMSON);

        RocketShipObject rocketShipObject = new RocketShipObject(new Vector3(900, 900, 0), new Vector3(0, 0, 0), 50000, PhysicsObjectType.Rocket, 10, 10, Color.BLUE);
    }

    @Override
    public void update()
    {
        updateForces();

        updateObjects();
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

                double productOfMassAndGravity = GRAVITY * physicsBodyOne.getMass() * physicsBodyTwo.getMass();


                Vector3 positionDifference = physicsBodyOne.getPosition().subtract(physicsBodyTwo.getPosition());

                double distance = Mathematics.getDistance(physicsBodyOne.getPosition(), physicsBodyTwo.getPosition());

                distance = Math.pow(distance, 2);


                Vector3 force = positionDifference.multiplyBy(productOfMassAndGravity);

                force = force.divideBy(distance);


                force = force.multiplyBy(-1);

                physicsBodyOneForce = physicsBodyOneForce.add(force);
            }

            physicsBodyOne.setForce(physicsBodyOneForce);

            physicsBodyOne.setAcceleration(physicsBodyOne.getForce().divideBy(physicsBodyOne.getMass()));
        }
    }

    private void updateObjects()
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }
}
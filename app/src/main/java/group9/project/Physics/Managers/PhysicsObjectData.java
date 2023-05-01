package group9.project.Physics.Managers;

import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;
import javafx.scene.paint.Color;

public class PhysicsObjectData implements IStartable
{
    //#region Singleton
    private static PhysicsObjectData instance;

    public static PhysicsObjectData getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsObjectData();
        }

        return instance;
    }
    //#endregion

    // The Good Initial Conditions ;(
    // private Vector3 rocketShipStartingPosition = new Vector3(-148186906.893642, -27823158.5715694 + 6370, 33746.8987977113);
    // private static Vector3 rocketShipStartingVelocity = new Vector3(43.07164438203265, -41.31788889917668, -3.8464189997186904);

    private Vector3 rocketShipStartingPosition = new Vector3(-148186906.893642, -27823158.5715694 + 6370, 33746.8987977113);

    private Vector3 rocketShipStartingVelocity = new Vector3(43.055263066324734, -41.35587532316244, -3.3992847916377094);


    private CelestialBodyObject titanObject;

    private RocketShipObject rocketShipObject;

    private PhysicsObjectData()
    {
        
    }

    public CelestialBodyObject getTitanObject()
    {
        return titanObject;
    }

    public RocketShipObject getRocketShipObject()
    {
        return rocketShipObject;
    }

    @SuppressWarnings("unused")
    @Override
    public void start()
    {
        CelestialBodyObject sunObject = new CelestialBodyObject(new Vector3(0, 0, 0), new Vector3(0, 0, 0), 1.99E+30, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Sun, 10, 25, Color.RED);

        CelestialBodyObject mercuryObject = new CelestialBodyObject(new Vector3(7833268.43923962, 44885949.3703908, 2867693.20054382), new Vector3(-57.4967480139828, 11.52095127176, 6.21695374334136), 3.30E+23, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Mercury, 6, 25, Color.YELLOW);

        CelestialBodyObject venusObject = new CelestialBodyObject(new Vector3(-28216773.9426889, 103994008.541512, 3012326.64296788), new Vector3(-34.0236737066136, -8.96521274688838, 1.84061735279188), 4.87E+24, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Venus, 10, 25, Color.PURPLE);

        CelestialBodyObject earthObject = new CelestialBodyObject(new Vector3(-148186906.893642, -27823158.5715694, 33746.8987977113), new Vector3(5.05251577575409, -29.3926687625899, 0.00170974277401292), 5.97E+24, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Earth, 4, 25, Color.GREEN);

        CelestialBodyObject moonObject = new CelestialBodyObject(new Vector3(-148458048.395164, -27524868.1841142, 70233.6499287411), new Vector3(4.34032634654904, -30.0480834180741, -0.0116103535014229), 7.35E+22, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Moon, 2, 15, Color.GRAY);

        CelestialBodyObject marsObject = new CelestialBodyObject(new Vector3(-159116303.422552,	189235671.561057, 7870476.08522969), new Vector3(-17.6954469224752, -13.4635253412947, 0.152331928200531), 6.42E+23, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Mars, 6, 25, Color.ORANGE);

        CelestialBodyObject jupiterObject = new CelestialBodyObject(new Vector3(692722875.928222,	258560760.813524, -16570817.7105996), new Vector3(-4.71443059866156, 12.8555096964427, 0.0522118126939208), 1.90E+27, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Jupiter, 16, 35, Color.PINK);

        CelestialBodyObject saturnObject = new CelestialBodyObject(new Vector3(1253801723.95465, -760453007.810989, -36697431.1565206), new Vector3(4.46781341335014,	8.23989540475628, -0.320745376969732), 5.68E+26, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Saturn, 12, 30, Color.BROWN);

        titanObject = new CelestialBodyObject(new Vector3(1254501624.95946, -761340299.067828,	-36309613.8378104), new Vector3(8.99593229549645, 11.1085713608453, -2.25130986174761), 1.35E+23, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Titan, 1, 15, Color.GOLD);

        CelestialBodyObject neptuneObject = new CelestialBodyObject(new Vector3(4454487339.09447, -397895128.763904, -94464151.3421107), new Vector3(0.447991656952326, 5.44610697514907, -0.122638125365954), 1.02E+26, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Neptune, 5, 25, Color.BLUE);

        CelestialBodyObject uranusObject = new CelestialBodyObject(new Vector3(1958732435.99338, 2191808553.21893, -17235283.8321992), new Vector3(-5.12766216337626,	4.22055347264457, 0.0821190336403063), 8.68E+25, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Uranus, 6, 25, Color.CRIMSON);

        rocketShipObject = new RocketShipObject(rocketShipStartingPosition, rocketShipStartingVelocity, 50000, PhysicsSettings.getUniverseDifferentialSolver(), PhysicsObjectType.Rocket, 6, 6, Color.BLUE);
    } 

    /**
    * @return the distance between the Rocket Ship and Titan
    */
    public double getRocketShipDistanceToTitan()
    {
        return Mathematics.calculateDistance(rocketShipObject.getPosition(), titanObject.getPosition());
    }

    /**
    * @return the direction vector to Titan from the Rocket Ship
    */
    public Vector3 getDirectionFromRocketShipToTitan()
    {
        return titanObject.getPosition().subtract(rocketShipObject.getPosition());
    }

    /**
    * @return the magnitude of the Rocket Ship's velocity
    */
    public double getRocketShipSpeed()
    {
        return rocketShipObject.getVelocity().getMagnitude();
    }
}
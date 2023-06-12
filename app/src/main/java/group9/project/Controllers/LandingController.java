package group9.project.Controllers;

import group9.project.Data.Data;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

public abstract class LandingController implements IController
{
    protected RocketShipObject rocketShip;

    protected Vector3 landingCoordinates;

    protected double mainThrusterAcceleration;

    protected double sideThrusterTorque;

    public double getMainThrusterAcceleration()
    {
        return Mathematics.clamp(mainThrusterAcceleration, Data.getMaxMainThrusterAcceleration());
    }

    public double getSideThrusterTorque()
    {
        return Mathematics.clamp(sideThrusterTorque, Data.getMaxTorque());
    }

    public LandingController(RocketShipObject newRocketShip)
    {
        rocketShip = newRocketShip;
    }

    public abstract void updateLandPath(Vector3 landingCoordinates);
}
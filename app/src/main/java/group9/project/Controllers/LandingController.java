package group9.project.Controllers;

import group9.project.Data.Data;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

public abstract class LandingController implements IController
{
    protected RocketShipObject rocketShip;

    protected double planetsGravity;

    protected double thrusterAcceleration;

    protected double thrusterRotation;

    public double getThrusterAcceleration()
    {
        return Mathematics.clamp(thrusterAcceleration, 0, Data.getMaxMainThrusterAcceleration() * planetsGravity);
    }

    public double getThrusterRotation()
    {
        return Mathematics.clamp(thrusterRotation, -Data.getMaxTorque(), Data.getMaxTorque());
    }

    public LandingController(RocketShipObject newRocketShip, double newPlanetsGravity)
    {
        rocketShip = newRocketShip;

        planetsGravity = newPlanetsGravity;
    }

    public abstract void updateLandPath(Vector3 landingCoordinates, double stepSize);
}
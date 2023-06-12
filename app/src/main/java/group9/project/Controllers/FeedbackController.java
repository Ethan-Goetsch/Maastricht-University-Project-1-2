package group9.project.Controllers;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

public class FeedbackController extends LandingController
{
    public FeedbackController(RocketShipObject newRocketShip)
    {
        super(newRocketShip);
    }

    @Override
    public void updateLandPath(Vector3 landingCoordinates)
    {
        double distanceToCoordinates = Mathematics.calculateDistance(rocketShip.getPosition(), landingCoordinates);

        mainThrusterAcceleration = distanceToCoordinates;
    }
}
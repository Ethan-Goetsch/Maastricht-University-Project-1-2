package group9.project.Controllers;

import group9.project.Data.Data;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Utility.Math.Vector3;
import group9.project.Wind.WindModel;

public class FeedbackController extends LandingController
{
    private WindModel windModel;

    public FeedbackController(RocketShipObject newRocketShip, double newPlanetsGravity, WindModel newWindModel)
    {
        super(newRocketShip, newPlanetsGravity);

        windModel = newWindModel;
    }

    @Override
    public void updateLandPath(Vector3 landingPosition, double stepSize)
    {
        double estimatedRotation = 0;

        double estimatedRotationVelocity = 0;

        double estimatedVelocity = 0;

        double distance = 0;


        double additionalMinimumAcceleration = 0;


        double xDistance = Math.abs(rocketShip.getPosition().getX() - landingPosition.getX());

        double yDistance = Math.abs(rocketShip.getPosition().getY() - landingPosition.getY());


        double wind = windModel.generateRandomWind(yDistance);


        if (xDistance > Data.getLandingToleranceX())
        {
            estimatedRotation = getTargetRotationForX(landingPosition);

            distance = xDistance;

            additionalMinimumAcceleration = 0;
        }
        else if (yDistance > Data.getLandingToleranceY())
        {
            estimatedRotation = getTargetRotationForY(landingPosition);

            distance = yDistance;

            additionalMinimumAcceleration = planetsGravity;
        }
        else
        {
            additionalMinimumAcceleration = planetsGravity;
        }

        estimatedRotationVelocity = (estimatedRotation - rocketShip.getRotation() + wind) / stepSize;

        estimatedVelocity = distance / stepSize;

        if (rocketShip.getRotation() != estimatedRotation)
        {
            thrusterRotation = (estimatedRotationVelocity - (wind / stepSize)) / stepSize;

            thrusterAcceleration = 0;
        }
        else
        {
            thrusterRotation = 0;

            thrusterAcceleration = estimatedVelocity / stepSize + additionalMinimumAcceleration;
        } 
    }

    private double getTargetRotationForX(Vector3 landingPosition)
    {
        return rocketShip.getPosition().getX() <= landingPosition.getX() ? 1/2.0 * Math.PI : 3/2.0 * Math.PI;
    }

    private double getTargetRotationForY(Vector3 landingPosition)
    {
        return rocketShip.getPosition().getY() <= landingPosition.getY() ? 0 : Math.PI;
    }
}
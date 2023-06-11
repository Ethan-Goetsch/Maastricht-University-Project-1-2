package group9.project.Controllers;

public class LandingController implements IController
{
    protected double mainThrusterAcceleration;

    protected double sideThrusterTorque;

    public double getMainThrusterAcceleration()
    {
        return mainThrusterAcceleration;
    }

    public double getSideThrusterTorque()
    {
        return sideThrusterTorque;
    }
}
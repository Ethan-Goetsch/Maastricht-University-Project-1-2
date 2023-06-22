package group9.project.Solvers;

import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Math.Vector3;

public class DiscreteSolver
{
    public Vector3[] calculateMotion(Vector3 position, double theta, double thrusterAcceleration, double h)
    {
        Vector3[] state = new Vector3[2];


        double xAcceleration = thrusterAcceleration * Math.sin(theta);

        double yAcceleration = thrusterAcceleration * Math.cos(theta) - PhysicsSettings.getTitansGravity();


        double newVelocityX = getValueAtPoint(0, xAcceleration, h);

        double newVelocityY = getValueAtPoint(0, yAcceleration, h);


        double newPositionX = getValueAtPoint(position.getX(), newVelocityX, h);

        double newPositionY = getValueAtPoint(position.getY(), newVelocityY, h);


        state[0] = new Vector3(newPositionX, newPositionY, 0);

        state[1] = new Vector3(newVelocityX, newVelocityY, 0);


        return state;
    }

    public double[] calculateRotation(double rotation, double rotationAcceleration, double h)
    {
        double[] state = new double[2];


        double newRotationVelocity = getValueAtPoint(0, rotationAcceleration, h);

        double newRotation = getValueAtPoint(rotation, newRotationVelocity, h);


        state[0] = newRotation % (2 * Math.PI);

        state[1] = newRotationVelocity % (2 * Math.PI);


        return state;
    }

    private double getValueAtPoint(double initialValue, double derivative, double h)
    {
        return initialValue + derivative * h;
    }
}
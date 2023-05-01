package group9.project.Hill_Climbing;

import group9.project.Utility.Math.Vector3;

public abstract class FuelSolution
{
    protected double score;

    protected double thrusterForce;

    protected Vector3 velocity;

    public double getScore()
    {
        return score;
    }

    public double getThrusterForce()
    {
        return thrusterForce;
    }

    public FuelSolution(double newThrusterForce, Vector3 newVelocity)
    {
        thrusterForce = newThrusterForce;

        velocity = newVelocity;

        calculateScore();
    }

    protected abstract void calculateScore();
}
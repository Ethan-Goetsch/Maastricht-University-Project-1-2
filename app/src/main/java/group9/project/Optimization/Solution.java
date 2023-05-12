package group9.project.Optimization;

import group9.project.Utility.Math.Vector3;

public class Solution
{
    private Vector3 velocity;

    private double thrusterForce;

    private double score;

    public Solution(Vector3 newVelocity, double newThrusterForce)
    {
        velocity = newVelocity;

        thrusterForce = newThrusterForce;

        score = Integer.MAX_VALUE;
    }

    public Vector3 getVelocity()
    {
        return velocity;
    }

    public double getThrusterForce()
    {
        return thrusterForce;
    }

    public double getScore()
    {
        return score;
    }

    public void setScore(double newScore)
    {
        score = newScore;
    }

    @Override
    public String toString()
    {
        return "Velocity : " + getVelocity() + " | " + "Force : " + getThrusterForce() + " | " + "Score : " + getScore();
    }
}
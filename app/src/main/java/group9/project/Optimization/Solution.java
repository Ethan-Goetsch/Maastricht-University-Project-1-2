package group9.project.Optimization;

import group9.project.Utility.Math.Vector3;

public class Solution
{
    private Vector3 velocity;

    private double force;

    private double score;

    public Solution(Vector3 newVelocity, double newForce, double newScore)
    {
        velocity = newVelocity;

        force = newForce;

        score = newScore;
    }

    public Vector3 getVelocity()
    {
        return velocity;
    }

    public double getForce()
    {
        return force;
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
        return "Score : " + getScore() + " | " + "Velocity : " + getVelocity() + " | " + "Force : " + getForce();
    }
}
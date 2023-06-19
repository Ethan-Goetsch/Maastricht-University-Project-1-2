package group9.project.Solutions;

import group9.project.Utility.Math.Vector3;

public class FuelSolution implements ISolution<Vector3>
{
    protected Vector3 soluionValue;

    protected double score;

    @Override
    public Vector3 getSolutionValue()
    {
        return soluionValue;
    }

    public double getScore()
    {
        return score;
    }

    public FuelSolution(Vector3 newSolutionValue)
    {
        soluionValue = newSolutionValue;

        score = Integer.MAX_VALUE;
    }

    public void setScore(double newScore)
    {
        score = newScore;
    }

    @Override
    public String toString()
    {
        return "Velocity : " + getSolutionValue() + " | " + "Score : " + getScore();
    }
}
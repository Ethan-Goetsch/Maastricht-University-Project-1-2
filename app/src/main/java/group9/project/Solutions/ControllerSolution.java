package group9.project.Solutions;

import group9.project.Utility.Math.Vector2;

public class ControllerSolution implements ISolution<Vector2>
{
    private Vector2 solutionValue;

    @Override
    public Vector2 getSolutionValue()
    {
        return solutionValue;
    }

    public ControllerSolution(Vector2 newSolutionValue)
    {
        solutionValue = newSolutionValue;
    }
}
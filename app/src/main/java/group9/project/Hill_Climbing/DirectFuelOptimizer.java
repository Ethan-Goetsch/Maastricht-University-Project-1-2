package group9.project.Hill_Climbing;

import java.util.ArrayList;
import java.util.List;

import group9.project.Utility.Math.Vector3;

public class DirectFuelOptimizer extends FuelOptimizer
{
    @Override
    protected void setInitialSolution(Vector3 flightVelocity)
    {
        initialSolution = new DirectFlightFuelSolution(10, flightVelocity);
    } 

    @Override
    protected double getAmountOfNeighbours()
    {
        return 20;
    }

    @Override
    protected double getMinNeighboursRange()
    {
        return -100;
    }

    @Override
    protected double getMaxNeighboursRange()
    {
        return 100;
    }

    @Override
    protected List<FuelSolution> generateNeighbourSolutions(FuelSolution solution, Vector3 flightVelocity)
    {
        List<FuelSolution> neighbourSolutions = new ArrayList<>();

        for (int i = 0; i < getAmountOfNeighbours(); i++)
        {
            neighbourSolutions.add(new DirectFlightFuelSolution(solution.getThrusterForce() + (Math.random() * getMaxNeighboursRange() + getMinNeighboursRange()), flightVelocity));
        }

        return neighbourSolutions;
    }
}
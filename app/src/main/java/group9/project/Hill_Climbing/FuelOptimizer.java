package group9.project.Hill_Climbing;

import java.util.List;

import group9.project.Utility.Math.Vector3;

public abstract class FuelOptimizer
{
    protected FuelSolution initialSolution;

    public double calculateOptimalThrusterForce(Vector3 flightVelocity)
    {
        setInitialSolution(flightVelocity);

        FuelSolution optimalSolution = calculateOptimalSolution(flightVelocity);

        return optimalSolution.getThrusterForce();
    }

    protected abstract void setInitialSolution(Vector3 flightVelocity);

    protected abstract double getAmountOfNeighbours();

    protected abstract double getMinNeighboursRange();

    protected abstract double getMaxNeighboursRange();

    protected abstract List<FuelSolution> generateNeighbourSolutions(FuelSolution solution, Vector3 flightVelocity);

    protected FuelSolution calculateOptimalSolution(Vector3 flightVelocity)
    {
        FuelSolution optimalSolution = initialSolution;

        while (true)
        {
            List<FuelSolution> neighbourSolutions = generateNeighbourSolutions(optimalSolution, flightVelocity);

            FuelSolution bestNeighbour = neighbourSolutions.get(0);

            for (FuelSolution fuelSolution : neighbourSolutions)
            {
                if (fuelSolution.getScore() >= bestNeighbour.getScore())
                {
                    bestNeighbour = fuelSolution;
                }    
            }

            if (optimalSolution.getScore() >= bestNeighbour.getScore())
            {
                return optimalSolution;
            }
            else
            {
                optimalSolution = bestNeighbour;
            }
        }
    }
}
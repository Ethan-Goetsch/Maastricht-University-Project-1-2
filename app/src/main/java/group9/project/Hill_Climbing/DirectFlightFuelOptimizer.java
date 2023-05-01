package group9.project.Hill_Climbing;

import java.util.ArrayList;
import java.util.List;

import group9.project.Utility.Math.Vector3;

public class DirectFlightFuelOptimizer extends FuelOptimizer
{
    @Override
    protected void setInitialSolution(Vector3 flightVelocity)
    {
        initialSolution = new DirectFlightFuelSolution(0, flightVelocity);
    } 

    @Override
    protected List<FuelSolution> generateNeighbourSolutions(FuelSolution solution, Vector3 flightVelocity)
    {
        List<FuelSolution> neighbourSolutions = new ArrayList<>();

        for (int i = -10; i < 10; i += 1)
        {
            neighbourSolutions.add(new DirectFlightFuelSolution(solution.getThrusterForce() + i, flightVelocity));
        }

        return neighbourSolutions;
    }
}
package group9.project.Hill_Climbing;

import group9.project.Utility.Math.Vector3;

public class DirectFlightFuelSolution extends FuelSolution
{
    public DirectFlightFuelSolution(double newThrusterForce, Vector3 newVelocity)
    {
        super(newThrusterForce, newVelocity);
    }

    @Override
    protected void calculateScore()
    {
        score = velocity.multiplyBy(thrusterForce).divideBy(2).getMagnitude() / thrusterForce; 
    }  
}
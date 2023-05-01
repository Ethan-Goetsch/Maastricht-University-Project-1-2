package group9.project.States.Rocket;

import java.util.List;

import group9.project.Hill_Climbing.DirectFlightFuelOptimizer;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Math.Vector3;

public class DirectFlightRocketState extends RocketState
{
    public DirectFlightRocketState(RocketShipObject newRocketShip, List<RocketState> newNeighbourStates)
    {
        super(newRocketShip, newNeighbourStates);

        fuelOptimizer = new DirectFlightFuelOptimizer();
    }

    @Override
    public void onStateEnter()
    {

    }

    @Override
    public void onStateExit()
    {

    }

    @Override
    public boolean canTransition()
    {
        return true;
    }

    @Override
    public void tick()
    {
        tickThrusters();

        tickMovement();
    }

    private void tickThrusters()
    {
        double optimalThrusterForce = fuelOptimizer.calculateOptimalThrusterForce(rocketShip.getVelocity());

        rocketShip.setThrusterForce(optimalThrusterForce);


        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepTime() - rocketShip.getThrusterForce();

        rocketShip.setVelocity(rocketShip.getVelocity().add(new Vector3(impulseForce / rocketShip.getMass(), 0, 0)));


        rocketShip.updateFuel(impulseForce);

        System.out.println(rocketShip.getFuel());
    }

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solveEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepTime());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }
}
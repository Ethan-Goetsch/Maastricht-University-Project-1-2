package group9.project.States.Rocket;

import java.util.List;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Math.Vector3;

public class DefaultRocketState extends RocketState
{
    public DefaultRocketState(RocketShipObject newRocketShip, List<RocketState> newNeighbourStates)
    {
        super(newRocketShip, newNeighbourStates);
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
        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getSimulationStepTime() - rocketShip.getThrusterForce();

        rocketShip.setVelocity(rocketShip.getVelocity().add(new Vector3(impulseForce / rocketShip.getMass(), 0, 0)));


        Vector3[] state = rocketShip.getDifferentialSolver().solveEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getSimulationStepTime());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);


        rocketShip.updateFuel(impulseForce);
    }
}
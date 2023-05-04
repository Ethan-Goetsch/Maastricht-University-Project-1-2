package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class TargetRocketState extends RocketState
{
    private ITargetable target;

    public TargetRocketState(RocketShipObject newRocketShip, ITargetable newTarget)
    {
        super(newRocketShip);

        target = newTarget;
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
    public void update()
    {
        tickThrusters();

        tickMovement();
    }

    private void tickThrusters()
    {
        // Vector3 directionToApplyMovement = trajectoryDirection.calculateDirectionToMove(rocketShip.getPosition(), target.getPosition());


        // double optimalThrusterForce = fuelOptimizer.calculateOptimalValue(rocketShip.getVelocity());

        // rocketShip.setThrusterForce(optimalThrusterForce);

        // rocketShip.applyForce(directionToApplyMovement.multiplyBy(rocketShip.getThrusterForce()));


        // double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepTime() - rocketShip.getThrusterForce();

        // rocketShip.applyVelocity(directionToApplyMovement.multiplyBy(impulseForce / rocketShip.getMass()));


        // rocketShip.updateFuel(impulseForce);
    }

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solveEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepTime(), rocketShip.getPhysicsObjectType());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }
}
package group9.project.States.Rocket;

import group9.project.Hill_Climbing.DirectFuelOptimizer;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Trajectory_Guidance.TargetTrajectoryDirection;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class LaunchRocketState extends RocketState
{
    private ITargetable target;

    public LaunchRocketState(RocketShipObject newRocketShip, ITargetable newTarget)
    {
        super(newRocketShip);

        target = newTarget;

        trajectoryDirection = new TargetTrajectoryDirection();

        fuelOptimizer = new DirectFuelOptimizer();

        onStateEnter();
    }

    @Override
    public void onStateEnter()
    {
        Vector3 directionToApplyMovement = trajectoryDirection.calculateDirectionToMove(rocketShip.getPosition(), target.getPosition());


        double optimalThrusterForce = fuelOptimizer.calculateOptimalThrusterForce(rocketShip.getVelocity());

        rocketShip.setThrusterForce(optimalThrusterForce);

        rocketShip.applyForce(directionToApplyMovement.multiplyBy(rocketShip.getThrusterForce()));


        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepTime() - rocketShip.getThrusterForce();

        rocketShip.applyVelocity(directionToApplyMovement.multiplyBy(impulseForce / rocketShip.getMass()));


        rocketShip.updateFuel(impulseForce);
    }

    @Override
    public void onStateExit()
    {

    }

    @Override
    public void update()
    {
        tickMovement();
    } 

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solveEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepTime(), rocketShip.getPhysicsObjectType());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }
}
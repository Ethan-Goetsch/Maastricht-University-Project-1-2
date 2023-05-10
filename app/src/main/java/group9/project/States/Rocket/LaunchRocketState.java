package group9.project.States.Rocket;

import group9.project.Optimization.Optimization;
import group9.project.Optimization.Solution;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector;
import group9.project.Utility.Math.Vector3;

public class LaunchRocketState extends RocketState
{
    private ITargetable target;

    public LaunchRocketState(RocketShipObject newRocketShip, ITargetable newTarget)
    {
        super(newRocketShip);

        target = newTarget;
    }

    @Override
    public void onStateEnter()
    {
        Solution optimalInitialParameters = Optimization.getInstance().getOptimalSolution();

        setInitialParameters(optimalInitialParameters.getVelocity(), optimalInitialParameters.getThrusterForce());
    }

    @Override
    public void onStateExit()
    {

    }

    @Override
    public void update()
    {
        tickMovement();

        tickThrusters();
    }

    private void setInitialParameters(Vector3 initialVelocity, double initialThrusterForce)
    {
        rocketShip.setVelocity(initialVelocity);

        rocketShip.setThrusterForce(initialThrusterForce);


        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepTime() - rocketShip.getThrusterForce();

        rocketShip.applyVelocity(Vector.calculateDirection(initialVelocity, impulseForce / rocketShip.getMass()));

        rocketShip.applyForce(Vector.calculateDirection(initialVelocity, rocketShip.getThrusterForce()));


        rocketShip.consumeFuel(rocketShip.getThrusterForce());
    }

    private void tickThrusters()
    {
        rocketShip.setThrusterForce(0);
    }

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solveEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepTime(), rocketShip.getPhysicsObjectType());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }
}
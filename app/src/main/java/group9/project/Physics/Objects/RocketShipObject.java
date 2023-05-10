package group9.project.Physics.Objects;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Solvers.DifferentialSolver;
import group9.project.States.IState;
import group9.project.States.IStateManager;
import group9.project.States.Rocket.*;
import group9.project.Utility.Math.Vector3;

public class RocketShipObject extends PhysicsObject implements IStateManager
{
    private RocketState currentRocketState;

    private double thrusterForce;

    private double impulseForce;

    private double fuelConsumed;

    public double getThrusterForce()
    {
        return thrusterForce;
    }

    public double getImpulseForce()
    {
        return impulseForce;
    }

    public double getFuelConsumed()
    {
        return fuelConsumed;
    }

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);
    }

    public void setThrusterForce(double newThrusterForce)
    {
        thrusterForce = newThrusterForce;
    }

    public void consumeFuel(double value)
    {
        fuelConsumed += value;
    }

    @Override
    public void start()
    {
        createRocketStates();
    }

    private void createRocketStates()
    {
        RocketState launchState = new LaunchRocketState(this, PhysicsObjectData.getInstance().getTitanObject());

        transitionToState(launchState);
    }

    @Override
    public void update()
    {
        updateAcceleration();

        updateState();
    }

    private void updateAcceleration()
    {
        setAcceleration(getForce().divideBy(getMass()));
    }

    private void updateState()
    {
        tickState();
    }

    @Override
    public void tickState()
    {
        currentRocketState.update();

        currentRocketState.checkStateTransitions();
    }

    @Override
    public void transitionToState(IState state)
    {
        if (currentRocketState != null)
        {
            currentRocketState.onStateExit();
        }

        currentRocketState = (RocketState) state;

        currentRocketState.onStateEnter();
    }
}
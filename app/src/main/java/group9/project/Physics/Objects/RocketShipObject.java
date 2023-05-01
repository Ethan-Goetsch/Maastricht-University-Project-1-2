package group9.project.Physics.Objects;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Solvers.DifferentialSolver;
import group9.project.States.IState;
import group9.project.States.IStateManager;
import group9.project.States.Rocket.TargetRocketState;
import group9.project.UI.Drawable.DrawableRocketShipUI;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.States.Rocket.RocketState;
import group9.project.Utility.Math.Vector3;
import javafx.scene.paint.Color;

public class RocketShipObject extends PhysicsObject implements IStateManager
{
    private RocketState currentRocketState;

    private DrawableUI drawableRocketShipUI;

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

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int shipWidth, int shipHeight, Color shipColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);

        drawableRocketShipUI = new DrawableRocketShipUI(shipWidth, shipHeight, newPhysicsObjectType.toString(), shipColour);

        createRocketStates();
    }

    private void createRocketStates()
    {
        currentRocketState = new TargetRocketState(this, PhysicsObjectData.getInstance().getTitanObject());
    }

    public void setThrusterForce(double newThrusterForce)
    {
        thrusterForce = newThrusterForce;
    }

    public void updateFuel(double value)
    {
        fuelConsumed += value;
    }

    @Override
    public void update()
    {
        updateMovement();

        updateDrawable();
    }

    private void updateMovement()
    {
        tickState();
    }

    private void updateDrawable()
    {
        drawableRocketShipUI.update(getPosition());
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
package group9.project.Physics.Objects;

import group9.project.Solvers.DifferentialSolver;
import group9.project.States.IStateManager;
import group9.project.States.Rocket.RocketStateManager;
import group9.project.UI.Drawable.DrawableRocketShipUI;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.Utility.Math.Vector3;
import javafx.scene.paint.Color;

public class RocketShipObject extends PhysicsObject
{
    private IStateManager rocketStateManager;

    private DrawableUI drawableRocketShipUI;

    private double thrusterForce;

    private double impulseForce;

    private double fuelConsumed;

    private double torque;

    private double torqueVelocity;
    
    /**
     * @return the current thruster force of the Physics Object
     */
    public double getThrusterForce()
    {
        return thrusterForce;
    }

    /**
     * @return the current impulse force of the Physics Object
     */
    public double getImpulseForce()
    {
        return impulseForce;
    }

    /**
     * @return the current fuel consumed of the Physics Object
     */
    public double getFuelConsumed()
    {
        return fuelConsumed;
    }

    public double getTorque()
    {
        return torque;
    }

    public double getTorqueVelocity()
    {
        return torqueVelocity;
    }

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int shipWidth, int shipHeight, Color shipColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);

        drawableRocketShipUI = new DrawableRocketShipUI(shipWidth, shipHeight, newPhysicsObjectType.toString(), shipColour, getPosition());
    }

    /**
     * Sets the Rocket Ships thruster force
     * 
     * @param newThrusterForce the new thruster force of the Rocket Ship
     */
    public void setThrusterForce(double newThrusterForce)
    {
        thrusterForce = newThrusterForce;
    }

    /**
     * Adds fuel to the Rocket Ship's fuel consumed
     * 
     * @param value the value to add to the Rocket Ship's fuel consumed
     */
    public void consumeFuel(double value)
    {
        fuelConsumed += value;
    }

    public void setTorque(double newTorque)
    {
        torque = newTorque;
    }

    public void setTorqueVelocity(double newTorqueVelocity)
    {
        torqueVelocity = newTorqueVelocity;
    }

    /**
     * Starts the Rocket Ship. Creates and sets the Rocket Ship's State Manager
     */
    @Override
    public void start()
    {
        rocketStateManager = new RocketStateManager(this);
    }

    /**
     * Updats the Rocket
     */
    @Override
    public void update()
    {
        updateAcceleration();

        updateState();

        updateDrawable();
    }

    /**
     * Updats the acceleration of the Rocket Ship using the acceleration equation given in the manual
     */
    private void updateAcceleration()
    {
        setAcceleration(getForce().divideBy(getMass()));
    }

    /**
     * Updats the State Manager of the Rocket Ship
     */
    private void updateState()
    {
        rocketStateManager.tickState();
    }

    /**
     * Updats the Drawable component of the Rocket Ship
     */
    private void updateDrawable()
    {
        drawableRocketShipUI.update(getPosition());
    }
}
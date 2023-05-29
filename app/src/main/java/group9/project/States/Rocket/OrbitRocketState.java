package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.States.IStateManager;
import group9.project.Utility.Interfaces.IBooleanFunction;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector;
import group9.project.Utility.Math.Vector3;

public class OrbitRocketState extends RocketState
{
    private ITargetable target;

    private IBooleanFunction canEnterOrbitFunction;

    private double orbitDuration;

    private double orbitStartTime;

    public OrbitRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, ITargetable newTarget, IBooleanFunction newCanEnterOrbitFunction, double newOrbitDuration)
    {
        super(newStateManager, newRocketShip);

        target = newTarget;

        canEnterOrbitFunction = newCanEnterOrbitFunction;

        orbitDuration = newOrbitDuration;
    }

    /**
     * @return true if the Rocket Ship is in its target planet's orbit
     */
    public boolean canEnterOrbit()
    {
        return canEnterOrbitFunction.evaluate();
    }

    /**
     * @return true if the Rocket Ship has orbited for the specified duration. If the time elapsed since the Rocket Started orbiting is greater than or equal to the orbitDuration
     */
    public boolean canExitOrbit()
    {
        return orbitDuration <= SimulationSettings.getSimulationTime() - orbitStartTime;
    }

    /**
     * Sets the orbit time to the Simulation's current time and Sets the thrusters so that the Rocket Ship can orbit the targeted planet.
     * The vector used in the SetThrusters method is a small vector I found, through manual testing, that was effective.
     */
    @Override
    public void onStateEnter()
    {
        orbitStartTime = SimulationSettings.getSimulationTime();

        setThrusters(new Vector3(0.5, 0, 0.5));
    }

    @Override
    public void onStateExit()
    {

    }

    /**
     * Updates the Rocket Ship's movement
     */
    @Override
    public void update()
    {
        tickMovement();
    }

    /**
     * Calculates the velocity required to slow down the Rocket Ship to match the velocityNeededToOrbit
     * Sets the velocity of the Rocket Ship to the slowDown and sets the thrusterForce of the Rocket Ship to the slowDownVelocity's magnitude.
     * Calculates the impulse applied to the Rocket Ship from using the thrusters. Formula is defined in the Manual.
     * Applies force and velocity based on the impulseForce in the direction of the slowDown velocity.
     * Consumes fuel based on the impulseForce.
     * After all calculations are complete it turns off the thrusters
     * 
     * @param velocityNeededToOrbit velocity needed to orbit the targeted planet
     */
    private void setThrusters(Vector3 velocityNeededToOrbit)
    {
        Vector3 slowDownVelocity = rocketShip.getVelocity().subtract(velocityNeededToOrbit).multiplyBy(-1);

        double slowDownThrusterForce = slowDownVelocity.getMagnitude();
        

        rocketShip.applyVelocity(Vector.calculateDirectionMultiplied(slowDownVelocity, slowDownThrusterForce));

        rocketShip.setThrusterForce(slowDownThrusterForce);


        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepSize() - rocketShip.getThrusterForce();

        rocketShip.applyVelocity(Vector.calculateDirectionMultiplied(slowDownVelocity, impulseForce / rocketShip.getMass()));

        rocketShip.applyForce(Vector.calculateDirectionMultiplied(slowDownVelocity, rocketShip.getThrusterForce()));


        rocketShip.consumeFuel(rocketShip.getThrusterForce());

        rocketShip.setThrusterForce(0);
    }

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solvePhysicsEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepSize(), rocketShip.getPhysicsObjectType());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }

    @Override
    public String getDescription()
    {
        return "Orbit " + target.getDescription() + " State";
    }
}
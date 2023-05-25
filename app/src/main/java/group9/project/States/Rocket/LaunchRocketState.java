package group9.project.States.Rocket;

import group9.project.Optimization.LaunchOptimization;
import group9.project.Optimization.Solution;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.States.IStateManager;
import group9.project.Utility.Math.Vector;
import group9.project.Utility.Math.Vector3;

public class LaunchRocketState extends RocketState
{
    private LaunchOptimization launchOptimization;

    public LaunchRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, LaunchOptimization newLaunchOptimization)
    {
        super(newStateManager, newRocketShip);

        launchOptimization = newLaunchOptimization;
    }

    /**
     * Gets the optimal launch parameters from the State's Launch Optimization
     */
    @Override
    public void onStateEnter()
    {
        Solution optimalInitialParameters = launchOptimization.generateOptimalSolution();

        setInitialParameters(optimalInitialParameters.getVelocity());
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
     * Sets the velocity of the Rocket Ship to the initialVelocity and sets the thrusterForce of the Rocket Ship to the initialVelocity's magnitude.
     * Calculates the impulse applied to the Rocket Ship from using the thrusters. Formula is defined in the Manual.
     * Applies force and velocity based on the impulseForce in the direction of the initialVelocity.
     * Consumes fuel based on the impulse force.
     * After all calculations are complete it turns off the thrusters
     * 
     * @param initialVelocoity the optimal launch velocity of the Rocet Ship
     */
    private void setInitialParameters(Vector3 initialVelocoity)
    {
        double initialThrusterForce = initialVelocoity.getMagnitude();
        

        rocketShip.setThrusterForce(initialThrusterForce);

        rocketShip.setVelocity(Vector.calculateDirectionMultiplied(initialVelocoity, initialThrusterForce));


        double impulseForce = rocketShip.getThrusterForce() * PhysicsSettings.getStepSize() - rocketShip.getThrusterForce();

        rocketShip.applyVelocity(Vector.calculateDirectionMultiplied(initialVelocoity, impulseForce / rocketShip.getMass()));

        rocketShip.applyForce(Vector.calculateDirectionMultiplied(initialVelocoity, rocketShip.getThrusterForce()));


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
        return "Launch State";
    }
}
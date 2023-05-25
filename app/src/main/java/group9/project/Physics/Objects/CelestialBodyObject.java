package group9.project.Physics.Objects;

import group9.project.Settings.PhysicsSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class CelestialBodyObject extends PhysicsObject
{

    double planetRadius;
    
    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass,  DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, double planetRadius)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);
        this.planetRadius = planetRadius;
    }

    /**
     * Starts the Celestial Body
     */
    @Override
    public void start()
    {

    }

    /**
     * Updats the Celestial Body
     */
    @Override
    public void update()
    {
        updateAcceleration();

        updateMovement();

    }

    /**
     * Updats the acceleration of the Celestial Body using the acceleration equation given in the manual
     */
    private void updateAcceleration()
    {
        setAcceleration(getForce().divideBy(getMass()));
    }

    /**
     * Updats the movement of the Celestial Body by approximating the body's next position and velocity using its differential solver
     */
    private void updateMovement()
    {
        Vector3[] state = differentialSolver.solvePhysicsEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsSettings.getStepSize(), physicsObjectType);

        setPosition(state[0]);

        setVelocity(state[1]);

    }

}
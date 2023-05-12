package group9.project.Physics.Objects;

import group9.project.Settings.PhysicsSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Utility.Math.Vector3;

public class CelestialBodyObject extends PhysicsObject
{

    private double planetRadius;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass,  DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, double planetRadius)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);
        this.planetRadius = planetRadius;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void update()
    {
        updateAcceleration();

        updateMovement();

    }

    private void updateAcceleration()
    {
        setAcceleration(getForce().divideBy(getMass()));
    }

    public double getRadius()
    {
        return planetRadius;
    }

    private void updateMovement()
    {
        Vector3[] state = differentialSolver.solveEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsSettings.getStepTime(), physicsObjectType);

        setPosition(state[0]);

        setVelocity(state[1]);

    }

}
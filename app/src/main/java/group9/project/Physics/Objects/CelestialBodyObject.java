package group9.project.Physics.Objects;

import group9.project.Settings.PhysicsSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.UI.Drawable.DrawableCelestialBodyUI;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.Utility.Math.Vector3;
import javafx.scene.paint.Color;

public class CelestialBodyObject extends PhysicsObject
{
    private DrawableUI drawableCelestialBody;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass,  DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, double planetRadius, double labelOffset, Color planetColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);

        drawableCelestialBody = new DrawableCelestialBodyUI(planetRadius, labelOffset, newPhysicsObjectType.toString(), planetColour);
    }

    @Override
    public void start()
    {
        
    }

    @Override
    public void update()
    {
        updateMovement();

        updateDrawable();
    }

    private void updateMovement()
    {
        Vector3[] state = differentialSolver.solveEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsSettings.getStepTime(), physicsObjectType);

        setPosition(state[0]);

        setVelocity(state[1]);
    }

    private void updateDrawable()
    {
        drawableCelestialBody.update(getPosition());
    }
}
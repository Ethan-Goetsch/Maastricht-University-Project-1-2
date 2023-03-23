package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private double planetScaledSize;

    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, PhysicsObjectType newPhysicsObjectType, double newScaledSize, Color planetColour)
    {
        super(startingPosition, startingVelocity, mass, newPhysicsObjectType);

        planetScaledSize = newScaledSize;


        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(newScaledSize);
    }

    @Override
    public void update()
    {
        setVelocity(EulerSolver.getNewVelocity(velocity, acceleration));

        setPosition(EulerSolver.getNewPosition(position, velocity));
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(position, planetScaledSize);

        shape.setCenterX(scaledVector.getX());

        shape.setCenterY(scaledVector.getY());
    }
}
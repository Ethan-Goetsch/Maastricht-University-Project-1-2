package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, PhysicsObjectType newPhysicsObjectType, double radius, Color planetColour)
    {
        super(startingPosition, startingVelocity, mass, newPhysicsObjectType);

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(radius);
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
    public void setShapePosition()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(position);

        shape.setCenterX(scaledVector.getX());

        shape.setCenterY(scaledVector.getY());
    }
}
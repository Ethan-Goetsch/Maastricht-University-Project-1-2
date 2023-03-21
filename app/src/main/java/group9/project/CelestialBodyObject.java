package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, String name, Color planetColour)
    {
        super(startingPosition, startingVelocity, mass, name);

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(ScaleConverter.scaleRadiusFromMass(mass));
    }

    @Override
    public void update()
    {
        setVelocity(EulerSolver.getNewVelocity(velocity, acceleration));

        //System.out.println(name + " pos: " + pos);
        setPosition(EulerSolver.getNewPosition(position, velocity));

        //System.out.println(name + " pos: " + pos);
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void setShapePosition()
    {
        Vector3 scaledVector = ScaleConverter.scaleToScreen(position);

        shape.setCenterX(scaledVector.getX());

        shape.setCenterY(scaledVector.getY());
    }
}
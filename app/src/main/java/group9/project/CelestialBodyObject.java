package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double planetRadius, Color planetColour)
    {
        super(startingPosition, startingVelocity, startingAcceleration);

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setCenterX(startingPosition.getX());

        shape.setCenterY(startingPosition.getY());

        shape.setRadius(planetRadius);
    }

    @Override
    public void applyForce(Vector3 newForce)
    {
        super.applyForce(newForce);
    }

    @Override
    public void setPosition(Vector3 newPosition)
    {
        super.setPosition(newPosition);

        shape.setCenterX(newPosition.getX());

        shape.setCenterY(newPosition.getY());
    }

    @Override
    public void start()
    {

    }

    @Override
    public void update()
    {
        velocity.add(acceleration);

        setPosition(position.add(velocity));

        acceleration.multiplyBy(0);
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }
}
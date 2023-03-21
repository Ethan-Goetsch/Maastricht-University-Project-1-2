package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double mass, String name, double planetRadius, Color planetColour)
    {
        super(startingPosition, startingVelocity, startingAcceleration, mass, name);

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(planetRadius);
    }

    @Override
    public void update()
    {
        Vector3 newVelocity = acceleration.multiplyBy(PhysicsEngine.STEP_TIME);

        setVelocity(velocity.add(newVelocity));

        //System.out.println(name + " pos: " + pos);
        setPosition(position.add(velocity));

        //System.out.println(name + " pos: " + pos);
        //setAcceleration(acceleration.multiplyBy(0));
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void setShapePosition()
    {
        shape.setCenterX(position.getX());

        shape.setCenterY(position.getY());
    }
}
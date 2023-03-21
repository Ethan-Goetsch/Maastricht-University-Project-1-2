package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double planetRadius, Color planetColourString, String name)
    {
        super(startingPosition, startingVelocity, startingAcceleration, name);

        shape = new Circle();

        shape.setFill(planetColourString);

        shape.setCenterX(startingPosition.getX());

        shape.setCenterY(startingPosition.getY());

        shape.setRadius(planetRadius);
    }


    public void setMass(double mass) 
    {
        this.mass = mass;
    }


    @Override
    public void applyForce(Vector3 newForce)
    {
        super.applyForce(newForce);
    }

    public void setPosition(Vector3 newPosition)
    {
        shape.setCenterX(newPosition.getX());

        shape.setCenterY(newPosition.getY());
    }

    @Override
    public void start()
    {
        velocity = new Vector3(0,0,0);
        acceleration = new Vector3(0,0,0);

    }

    @Override
    public void update(double timeDelta)
    {
        super.update(timeDelta);
        setPosition(position.add(velocity));
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }
}
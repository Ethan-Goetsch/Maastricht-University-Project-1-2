package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RocketShipObject extends PhysicsObject
{
    private Rectangle shape;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, int width, int height, Color shipColour, String name)
    {
        super(startingPosition, startingVelocity, startingAcceleration, name);

        shape = new Rectangle();
        
        shape.setFill(shipColour);

        shape.setWidth(width);
        
        shape.setHeight(height);

        shape.setX(startingPosition.getX());

        shape.setY(startingPosition.getY());
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

        shape.setX(newPosition.getX());

        shape.setY(newPosition.getY());
    }


    @Override
    public void start()
    {

    }

    @Override
    public Shape getShape() {
        return shape;
    }

}
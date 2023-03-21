package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RocketShipObject extends PhysicsObject
{
    private Rectangle shape;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double mass, String name, int width, int height, Color shipColour)
    {
        super(startingPosition, startingVelocity, startingAcceleration, mass, name);

        shape = new Rectangle();
        
        shape.setFill(shipColour);

        shape.setWidth(width);
        
        shape.setHeight(height);
    }

    @Override
    public void update()
    {
        
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void setShapePosition()
    {
        shape.setX(position.getX());

        shape.setY(position.getY());
    }
}
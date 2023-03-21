package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RocketShipObject extends PhysicsObject
{
    private Rectangle shape;
    private Rectangle arrow;

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
        Vector3 newVelocity = acceleration.multiplyBy(PhysicsEngine.STEP_TIME);

        setVelocity(velocity.add(newVelocity));

        //System.out.println(name + " pos: " + pos);
        setPosition(position.add(velocity));

        //System.out.println(name + " pos: " + pos);
        setAcceleration(acceleration.multiplyBy(0));
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void setShapePosition()
    {
        Vector3 scaledPosition = Converter.scaleToScreen(position);
        shape.setX(scaledPosition.getX());

        shape.setY(scaledPosition.getY());
    }
}
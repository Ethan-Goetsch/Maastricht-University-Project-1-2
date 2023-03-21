package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RocketShipObject extends PhysicsObject
{
    private Rectangle shape;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, String name, int width, int height, Color shipColour)
    {
        super(startingPosition, startingVelocity, mass, name);

        shape = new Rectangle();
        
        shape.setFill(shipColour);

        shape.setWidth(width);
        
        shape.setHeight(height);
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
        Vector3 scaledVector = ScaleConverter.scaleToScreen(position);

        shape.setX(scaledVector.getX());

        shape.setY(scaledVector.getY());
    }
}
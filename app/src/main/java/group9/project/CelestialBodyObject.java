package group9.project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class CelestialBodyObject extends PhysicsObject
{
    private Circle shape;
    private Rectangle arrow;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, Vector3 startingAcceleration, double mass, String name, double planetRadius, Color planetColour)
    {
        super(startingPosition, startingVelocity, startingAcceleration, mass, name);

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(planetRadius);

        arrow = new Rectangle();
        arrow.setFill(Color.BLACK);
        arrow.setWidth(3);
        arrow.setHeight(15);
    }

    @Override
    public void update()
    {
        setVelocity(velocity.add(acceleration));

        //System.out.println(name + " pos: " + pos);
        setPosition(position.add(velocity.multiplyBy(3600*12)));

        //System.out.println(name + " pos: " + pos);
        setAcceleration(acceleration.multiplyBy(0));
    }

    @Override
    public Shape getShape()
    {
        return shape;
    }

    public Shape getArrow() {
        return arrow;
    }

    @Override
    public void setShapePosition()
    {
        Vector3 scaledPosition = Converter.scaleToScreen(position);
        shape.setCenterX(scaledPosition.getX());

        shape.setCenterY(scaledPosition.getY());

        /* 
        arrow.setX(position.getX());
        arrow.setY(position.getY());
        Rotate rotate = new Rotate();
        rotate.setAngle(Math.atan2(velocity.getX(), velocity.getY()));
        */
    }
}
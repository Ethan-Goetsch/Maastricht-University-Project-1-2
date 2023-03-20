package group9.project;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject implements IDrawable
{
    private Circle shape;

    public CelestialBodyObject(String name)
    {
        super(name);
        shape = new Circle();
    }

    public void setRadius(double d)
    {
        shape.setRadius(d);
    }

    public void setPosition(Vector3 pos)
    {
        this.pos = pos;

        shape.setCenterX(pos.getX());

        shape.setCenterY(pos.getY());
    }

    public void setMass(double mass) 
    {
        this.mass = mass;
    }


    public Shape getShape()
    {
        return shape;
    }

    @Override
    public void start()
    {
        velocity = new Vector3(0,0,0);
        acceleration = new Vector3(0,0,0);
    }

}
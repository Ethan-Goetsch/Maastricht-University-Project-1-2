package group9.project;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CelestialBodyObject extends PhysicsObject implements IDrawable
{
    private Circle shape;

    public CelestialBodyObject() {
        shape = new Circle();
    }

    public void setRadius(float radius) {
        shape.setRadius(radius);
    }

    public void setPosition(Vector3 pos) {
        this.pos = pos;
        shape.setCenterX(pos.getX());
        shape.setCenterY(pos.getY());
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public void applyForce(Vector3 force) {
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
    
}
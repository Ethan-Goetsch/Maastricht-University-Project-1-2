package Objects;

import Calculations.PhysicsCalculation;
import Components.PhysicsComponent;
import Interfaces.PhysicsItem;
import Managers.PhysicsController;
import Utility.Vector2;

public abstract class PhysicsObject implements PhysicsItem
{
    protected PhysicsComponent physicsComponent;

    protected PhysicsCalculation physicsCalculation;

    protected Vector2 position;

    protected Vector2 velocity;

    public Vector2 getPosition()
    {
        return position;
    }

    public Vector2 getDerivative()
    {
        return new Vector2(velocity.getXDouble() + position.getXDouble(), velocity.getYDouble() + position.getYDouble());
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public PhysicsComponent getPhysicsComponent()
    {
        return physicsComponent;
    }

    public PhysicsObject(Vector2 startPosition, Vector2 newVelocity)
    {
        position = startPosition;

        velocity = newVelocity;

        PhysicsController.getInstance().addPhysicsItem(this);
    }

    public abstract void start();

    public abstract void fixedUpdate();
}
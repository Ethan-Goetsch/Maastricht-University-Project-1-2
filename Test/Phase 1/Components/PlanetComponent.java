package Components;

import java.awt.Color;
import java.awt.Graphics;

import Objects.PhysicsObject;

public class PlanetComponent extends PhysicsComponent
{
    public PlanetComponent(PhysicsObject newPhysicsObject)
    {
        super(newPhysicsObject);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        graphics.setColor(Color.BLUE);

        graphics.fillOval(physicsObject.getPosition().getXInt(), physicsObject.getPosition().getYInt(), 75, 75);
    }
}
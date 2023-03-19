package Components;

import java.awt.Color;
import java.awt.Graphics;
import Objects.RocketshipObject;

public class RocketShipComponent extends PhysicsComponent
{
    public RocketShipComponent(RocketshipObject rocketShipObject)
    {
        super(rocketShipObject);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        graphics.setColor(Color.BLACK);

        graphics.drawRect(physicsObject.getPosition().getXInt(), physicsObject.getPosition().getYInt(), 50, 50);
    }
}
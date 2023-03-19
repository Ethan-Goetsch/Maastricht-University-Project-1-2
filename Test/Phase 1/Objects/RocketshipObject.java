package Objects;

import Calculations.*;
import Utility.*;
import Components.*;

public class RocketshipObject extends PhysicsObject
{
    public RocketshipObject(Vector2 startPosition, Vector2 velocity)
    {
        super(startPosition, velocity);

        physicsComponent = new RocketShipComponent(this);
    }

    @Override
    public void start()
    {
        physicsCalculation = new EulerCalculation();
    }

    @Override
    public void fixedUpdate()
    {
        position.set(physicsCalculation.calculateDistanceTravelled(this));
    }
}
package Objects;

import Calculations.EulerCalculation;
import Components.PlanetComponent;
import Utility.Vector2;

public class PlanetObject extends PhysicsObject
{
    public PlanetObject(Vector2 startPosition, Vector2 velocity)
    {
        super(startPosition, velocity);

        physicsComponent = new PlanetComponent(this);
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
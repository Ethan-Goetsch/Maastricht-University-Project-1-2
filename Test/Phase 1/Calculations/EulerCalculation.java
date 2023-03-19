package Calculations;

import Managers.PhysicsEngine;
import Objects.PhysicsObject;
import Utility.Vector2;
import Utility.VectorExtensions;

public class EulerCalculation extends PhysicsCalculation
{
    @Override
    public Vector2 calculateDistanceTravelled(PhysicsObject physicsObject)
    {
        Vector2 derivative = physicsObject.getDerivative();

        derivative.multiply(PhysicsEngine.STEP_TIME);

        return VectorExtensions.addVectors(physicsObject.getPosition(), derivative);
    }
}
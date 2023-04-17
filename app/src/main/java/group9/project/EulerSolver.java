package group9.project;

public class EulerSolver extends DifferentialSolver
{
    @Override
    public void solveEquation(PhysicsObject physicsObject)
    {
        Vector3 positionMoved = physicsObject.getVelocity().multiplyBy(PhysicsEngine.getSimulationStepTime());

        Vector3 velocityMoved = physicsObject.getAcceleration().multiplyBy(PhysicsEngine.getSimulationStepTime());


        physicsObject.setPosition(physicsObject.getPosition().add(positionMoved));

        physicsObject.setVelocity(physicsObject.getVelocity().add(velocityMoved));
    }   
}
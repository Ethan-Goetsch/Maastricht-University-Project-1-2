package group9.project;

public class EulerSolver
{
    public static Vector3 getNewVelocity(Vector3 currentVelocity, Vector3 currentAcceleration)
    {
        Vector3 derivative = currentAcceleration.multiplyBy(PhysicsEngine.getSimulationStepTime());

        return currentVelocity.add(derivative);
    }

    public static Vector3 getNewPosition(Vector3 currentPosition, Vector3 currentVelocity)
    {
        Vector3 derivative = currentVelocity.multiplyBy(PhysicsEngine.getSimulationStepTime());

        return currentPosition.add(derivative);
    }   
}
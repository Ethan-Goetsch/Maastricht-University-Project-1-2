package group9.project;

public class EulerSolver
{
    public static Vector3 getNewVelocity(Vector3 currentVelocity, Vector3 currentAcceleration)
    {
        Vector3 derivative = currentAcceleration.multiplyBy(PhysicsEngine.STEP_TIME);

        return currentVelocity.add(derivative);
    }

    public static Vector3 getNewPosition(Vector3 currentPosition, Vector3 currentVelocity)
    {
        Vector3 derivative = currentVelocity.multiplyBy(PhysicsEngine.STEP_TIME);

        return currentPosition.add(derivative);
    }   
}
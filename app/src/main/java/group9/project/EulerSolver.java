package group9.project;

public class EulerSolver
{
    public static Vector3 getNewVelocity(Vector3 currentVelocity, Vector3 currentAcceleration)
    {
        Vector3 derivative = currentAcceleration.multiplyBy(1);

        return currentVelocity.add(derivative);
    }

    public static Vector3 getNewPosition(Vector3 currentPosition, Vector3 currentVelocity)
    {
        Vector3 derivative = currentVelocity.multiplyBy(3600); // 3600 seconds = 1 hour (speeds up simulation)

        return currentPosition.add(derivative);
    }   
}
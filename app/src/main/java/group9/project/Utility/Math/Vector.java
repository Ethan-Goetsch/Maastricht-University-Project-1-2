package group9.project.Utility.Math;

public class Vector
{
    public static Vector3 calculateDirection(Vector3 direction, double number)
    {
        return direction.normalize().multiplyBy(number);
    }
}
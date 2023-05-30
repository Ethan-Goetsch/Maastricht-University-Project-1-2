package group9.project.Utility.Math;

public class Vector
{
    /**
     * 
     * @param vector 
     * @param otherVector
     * @return a new vector pointing from vector to otherVector
     */
    public static Vector3 calculateDirection(Vector3 vector, Vector3 otherVector)
    {
        return otherVector.subtract(vector);
    }

    public static Vector3 calculateDirectionMultiplied(Vector3 direction, double number)
    {
        return direction.normalize().multiplyBy(number);
    }

    public static Vector3 calculateSquareRoot(Vector3 vector)
    {
        double x = Math.sqrt(vector.getX());

        double y = Math.sqrt(vector.getY());

        double z = Math.sqrt(vector.getZ());


        return new Vector3(x, y, z);
    }

    public static double calculateDotProduct(Vector3 vector, Vector3 otherVector)
    {
        return vector.getX() * otherVector.getX() + vector.getY() * otherVector.getY() + vector.getZ() * otherVector.getZ();
    }
}
package group9.project.Utility.Math;

public class Mathematics
{
    public static int calculatePercentage(int number, int percentage)
    {
        return (number / 100) * percentage;
    }

    public static double calculateDistance(Vector3 positionOne, Vector3 positionTwo)
    {
        double x = Math.pow(positionOne.getX() - positionTwo.getX(), 2);

        double y = Math.pow(positionOne.getY() - positionTwo.getY(), 2);

        double z = Math.pow(positionOne.getZ() - positionTwo.getZ(), 2);

        return Math.sqrt(x + y + z);
    }

    public static double clamp(double value, double minValue, double maxValue)
    {
        return Math.max(minValue, Math.min(value, maxValue));
    }

    public static double clampCircular(double value, double minValue, double maxValue)
    {
        return value > maxValue ? minValue : value;
    }

    public static double degreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }
}
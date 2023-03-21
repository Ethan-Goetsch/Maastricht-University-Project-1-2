package group9.project;

public class Mathematics
{
    public static int getPercentage(int number, int percentage)
    {
        return (number / 100) * percentage;
    }

    public static double getDistance(Vector3 positionOne, Vector3 positionTwo)
    {
        double x = Math.pow(positionOne.getX() - positionTwo.getX(), 2);

        double y = Math.pow(positionOne.getY() - positionTwo.getY(), 2);

        double z = Math.pow(positionOne.getZ() - positionTwo.getZ(), 2);

        return Math.sqrt(x + y + z);
    }
}
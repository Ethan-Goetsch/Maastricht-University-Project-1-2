public class Vector3
{
    private double x;

    private double y;

    

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public Vector3()
    {
        x = 0;

        y = 0;

        z = 0;
    }

    public Vector3(double newX, double newY, double newZ)
    {
        x = newX;

        y = newY;

        z = newZ;
    }
}
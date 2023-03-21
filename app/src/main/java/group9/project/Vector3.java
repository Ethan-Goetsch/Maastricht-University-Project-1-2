package group9.project;

public class Vector3
{
    private double x;

    private double y;

    private double z;

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

    public Vector3 clone()
    {
        return new Vector3(this.x, this.y, this.z);
    }

    public Vector3 add(Vector3 vector)
    {
        return new Vector3(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    public Vector3 subtract(Vector3 vector)
    {
        return new Vector3(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }

    public Vector3 divideBy(double number)
    {
        return new Vector3(this.x / number, this.y / number, this.z / number);
    }

    public Vector3 multiplyBy(double number)
    {
        return new Vector3(this.x * number, this.y * number, this.z * number);
    }

    public double getMagnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3 normalize()
    {
        Vector3 newVector = this.clone();

        return newVector.divideBy(this.getMagnitude());
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ", " + z +")";
    }
}
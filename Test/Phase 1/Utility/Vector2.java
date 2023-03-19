package Utility;
public class Vector2
{
    private double x;

    private double y;

    public double getXDouble()
    {
        return x;
    }

    public double getYDouble()
    {
        return y;
    }

    public int getXInt()
    {
        return (int)x;
    }

    public int getYInt()
    {
        return (int)y;
    }

    public void setX(double value)
    {
        x = value;
    }

    public void setX(int value)
    {
        x = value;
    }

    public void setY(double value)
    {
        y = value;
    }

    public void setY(int value)
    {
        y = value;
    }

    public Vector2()
    {
        x = 0;

        y = 0;
    }

    public Vector2(double newX, double newY)
    {
        x = newX;

        y = newY;
    }

    public Vector2(int newX, int newY)
    {
        x = newX;

        y = newY;
    }

    public void set(Vector2 vector2)
    {
        x = vector2.getXDouble();

        y = vector2.getYDouble();
    }

    public void add(Vector2 vector2)
    {
        x += vector2.getXDouble();

        y += vector2.getYDouble();
    }

    public void multiply(Vector2 vector2)
    {

    }

    public void multiply(int value)
    {
        x *= value;

        y *= value;
    }

    public void multiply(double value)
    {
        x *= value;

        y *= value;
    }
}
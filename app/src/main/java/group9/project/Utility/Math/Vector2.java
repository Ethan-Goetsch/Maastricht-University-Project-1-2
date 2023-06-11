package group9.project.Utility.Math;

public class Vector2
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

    public Vector2 clone()
    {
        return new Vector2(this.x, this.y);
    }

    public Vector2 add(Vector3 vector)
    {
        return new Vector2(this.x + vector.getX(), this.y + vector.getY());
    }

    public Vector2 subtract(Vector3 vector)
    {
        return new Vector2(this.x - vector.getX(), this.y - vector.getY());
    }

    public Vector2 divideBy(double number)
    {
        return new Vector2(this.x / number, this.y / number);
    }

    public Vector2 multiplyBy(double number)
    {
        return new Vector2(this.x * number, this.y * number);
    }

    public double getMagnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2 normalize()
    {
        Vector2 newVector = this.clone();

        return newVector.divideBy(this.getMagnitude());
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
package Utility;

public class VectorExtensions
{
    public static Vector2 addVectors(Vector2 vector2One, Vector2 vector2Two)
    {
        Vector2 newVector2One = new Vector2(vector2One.getXDouble(), vector2One.getYDouble());

        Vector2 newVector2Two = new Vector2(vector2Two.getXDouble(), vector2Two.getYDouble());

        newVector2One.add(newVector2Two);

        return newVector2One;
    }
}
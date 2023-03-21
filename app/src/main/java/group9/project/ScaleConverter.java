package group9.project;

public class ScaleConverter
{
    private static final double SCREEN_SCALE_FACTOR = 60;

    private static final double CONSTANT_SCALE_FACTOR = 100000;

    private static final double RADIUS_SCALE_FACTOR = 35;

    public static Vector3 scaleToScreen(Vector3 vector)
    {
        double x = (vector.getX() / (CONSTANT_SCALE_FACTOR * SCREEN_SCALE_FACTOR)) + PhysicsVisualizer.getCanvasWidth() / 2;

        double y = (vector.getY() / (CONSTANT_SCALE_FACTOR * SCREEN_SCALE_FACTOR)) + PhysicsVisualizer.getCanvasHeight() / 2;

        double z = vector.getZ();

        return new Vector3(x, y, z);
    }

    public static double scaleRadiusFromMass(double mass)
    {       
        double newRadius = mass / 1.99E+30;

        if (newRadius < 1)
        {
            newRadius += 0.5;
        }

        newRadius *= RADIUS_SCALE_FACTOR;

        System.out.println(newRadius);

        return newRadius;
    }
}
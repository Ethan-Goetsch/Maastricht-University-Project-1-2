package group9.project;

public class ScaleConverter
{
    private static final double SCALE_KM_TO_PIXELS = 3779528.0352161;

    private static double scaleFactor = 3;

    public static double getScaleFactor()
    {
        return scaleFactor;
    }

    public static void setScaleFactor(double newScaleFactor)
    {
        scaleFactor = newScaleFactor;
    }

    private static double getSimulationScale()
    {
        return SCALE_KM_TO_PIXELS * scaleFactor;
    }

    public static Vector3 scaleToScreen(Vector3 vector)
    {
        double x = vector.getX() / getSimulationScale() + PhysicsVisualizer.getCanvasWidth() / 2;

        double y = vector.getY() / getSimulationScale() + PhysicsVisualizer.getCanvasHeight() / 2;

        double z = vector.getZ();

        return new Vector3(x, y, z);
    }
}
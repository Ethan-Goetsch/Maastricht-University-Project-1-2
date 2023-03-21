package group9.project;

public class ScaleConverter
{
    private static final double SCALE_KM_TO_UNITS = 3779528.0352161 * 3;

    public static Vector3 scaleToScreen(Vector3 vector)
    {
        double x = vector.getX() / SCALE_KM_TO_UNITS + PhysicsVisualizer.getCanvasWidth() / 2;

        double y = vector.getY() / SCALE_KM_TO_UNITS + PhysicsVisualizer.getCanvasHeight() / 2;

        double z = vector.getZ();

        return new Vector3(x, y, z);
    }
}
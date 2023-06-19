package group9.project.UI;

import group9.project.MissionControl;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class ScaleConverter implements IStartable
{
    //#region Singleton
    private static ScaleConverter instance;

    public static ScaleConverter getInstance()
    {
        if (instance == null)
        {
            instance = new ScaleConverter();
        }

        return instance;
    }
    //#endregion  

    private static final double SCALE_KM_TO_PIXELS = 3779528.0352161;

    private static double scaleFactor = 0.001;

    private static double scaleSize = 1;

    private static ITargetable worldCentreTaget;

    public static double getScaleFactor()
    {
        return scaleFactor;
    }

    public static double getScaleSize()
    {
        return scaleSize;
    }

    private static double getSimulationScale()
    {
        return SCALE_KM_TO_PIXELS * scaleFactor;
    }

    private ScaleConverter()
    {

    }

    public static void setScaleFactor(double newScaleFactor)
    {
        scaleFactor = newScaleFactor;
    }

    public static void setScaleSize(double newScaleSize)
    {
        scaleSize = newScaleSize;
    }

    public static void setWorldCentreTarget(ITargetable newWorldCentreTarget)
    {
        worldCentreTaget = newWorldCentreTarget;
    }

    @Override
    public void start()
    {
        worldCentreTaget = PhysicsObjectData.getInstance().getSunObject();
    }

    private static Vector3 getScreenCentrePosition()
    {
        double x = MissionControl.getWidth() / 2;

        double y = MissionControl.getHeight() / 2;

        double z = 0;

        return new Vector3(x, y, z);
    }
    
    public static double worldToScreenLength(double length)
    {
        return length / getSimulationScale();
    }

    public static Vector3 worldToScreenPosition(Vector3 worldPosition)
    {
        //double x = worldPosition.getX() / getSimulationScale() + Main.WIDTH / 2;
        double x = worldPosition.getX() / getSimulationScale();

        //double y = worldPosition.getY() / getSimulationScale() + Main.HEIGHT / 2;
        double y = worldPosition.getY() / getSimulationScale();

        double z = 0;

        return new Vector3(x, y, z);
        //return worldPosition.subtract(worldCentreTaget.getPosition()).divideBy(getSimulationScale()).add(getScreenCentrePosition());
    }
}
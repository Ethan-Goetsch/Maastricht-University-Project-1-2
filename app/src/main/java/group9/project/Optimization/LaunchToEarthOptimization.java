package group9.project.Optimization;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
import group9.project.Solutions.FuelSolution;
import group9.project.Utility.Math.Vector3;

public class LaunchToEarthOptimization extends LaunchOptimization
{
    //#region Singleton
    private static LaunchToEarthOptimization instance;

    public static LaunchToEarthOptimization getInstance()
    {
        if (instance == null)
        {
            instance = new LaunchToEarthOptimization();
        }

        return instance;
    }
    //#endregion

    private FuelSolution optimalSolution;

    private LaunchToEarthOptimization()
    {
        super();

        optimalSolution = new FuelSolution(new Vector3(-97.79701684304996, 59.335035090461716, 3.23738089765268));

        currentSolution = optimalSolution;
    }

    /**
     * @return true if the Simulation Setting's development mode is equal to Earth
     */
    @Override
    public boolean getIsOptimizationDevelopmentMode()
    {
        return getCanStartOptimizing() && SimulationSettings.getOptimizationDevelopmentMode() == OptimizationDevelopmentMode.Earth;
    }

    @Override
    protected FuelSolution getOptimalSolution()
    {
        return optimalSolution;
    }

    @Override
    protected void setOptimalSolution(FuelSolution newSolution)
    {
        optimalSolution = newSolution;
    }

    /**
     * @return the current distance between the Rocket Ship and Earth
     */
    @Override
    protected double getScoreCondition()
    {
        return PhysicsObjectData.getInstance().getRocketShipDistanceToEarth();
    }

    @Override
    protected boolean getIsOptimalCondition()
    {
        return PhysicsObjectData.getInstance().isRocketShipInEarthOrbit();
    }
}
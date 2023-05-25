package group9.project.Optimization;

import group9.project.Data.Data;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
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

    private Solution optimalSolution;

    private LaunchToEarthOptimization()
    {
        super();

        optimalSolution = new Solution(new Vector3(-97.23251344057303, 60.70885561328722, 0.8184370120541349));

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
    protected Solution getOptimalSolution()
    {
        return optimalSolution;
    }

    @Override
    protected void setOptimalSolution(Solution newSolution)
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
        return Data.inOrbit(getOptimalSolution().getScore());
    }
}
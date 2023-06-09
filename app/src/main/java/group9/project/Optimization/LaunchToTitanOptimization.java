package group9.project.Optimization;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
import group9.project.Solutions.FuelSolution;
import group9.project.Utility.Math.Vector3;

public class LaunchToTitanOptimization extends LaunchOptimization
{
    //#region Singleton
    private static LaunchToTitanOptimization instance;

    public static LaunchToTitanOptimization getInstance()
    {
        if (instance == null)
        {
            instance = new LaunchToTitanOptimization();
        }

        return instance;
    }
    //#endregion

    // Genetic Algorithm Initial Conditions To Reach Titan:
    // new Solution(new Vector3(43.055263066324734, -41.35587532316244, -3.3992847916377094)), 59.79648182418462);

    private FuelSolution optimalSolution;

    private LaunchToTitanOptimization()
    {
        super();

        optimalSolution = new FuelSolution(new Vector3(51.732619138489426, -37.57185241528165, -2.2535619861704554));

        currentSolution = optimalSolution;
    }

    /**
     * @return true if the Simulation Setting's development mode is equal to Titan
     */
    @Override
    public boolean getIsOptimizationDevelopmentMode()
    {
        return getCanStartOptimizing() && SimulationSettings.getOptimizationDevelopmentMode() == OptimizationDevelopmentMode.Titan;
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
     * @return the current distance between the Rocket Ship and Titan
     */
    @Override
    protected double getScoreCondition()
    {
        return PhysicsObjectData.getInstance().getRocketShipDistanceToTitan();
    }

    @Override
    protected boolean getIsOptimalCondition()
    {
        return PhysicsObjectData.getInstance().isRocketShipInTitanOrbit();
    }
}
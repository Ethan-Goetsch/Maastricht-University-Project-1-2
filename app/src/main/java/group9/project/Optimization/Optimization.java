package group9.project.Optimization;

import java.util.ArrayList;
import java.util.List;

import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Settings.SimulationSettings;
import group9.project.Utility.Interfaces.IResetable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Vector3;

public class Optimization implements IUpdateable, IResetable
{
    //#region Singleton
    private static Optimization instance;

    public static Optimization getInstance()
    {
        if (instance == null)
        {
            instance = new Optimization();
        }

        return instance;
    }
    //#endregion

    private final static int AMOUNT_OF_NEIGHBOURS = 10;


    private final static double MAX_NEIGHBOUR_VELOCITY_DIFFERENCE = 0.5;

    private final static double MIN_NEIGHBOUR_VELOCITY_DIFFERENCE = 0.5;


    private final static double MAX_NEIGHBOUR_FORCE_DIFFERENCE = 0.25;

    private final static double MIN_NEIGHBOUR_FORCE_DIFFERENCE = 0.25;


    private List<Solution> currentNeighbours = new ArrayList<>();

    private Solution optimalSolution = new Solution(new Vector3(53.30770478376297, -37.90506754552283, 0.754782488353855), 5.269428369727088, 1.068334069897194E7);

    private Solution currentSolution;

    // Genetic Algorithm Initial Velocity :
    // 43.055263066324734, -41.35587532316244, -3.3992847916377094

    private Optimization()
    {
        currentSolution = optimalSolution; 
    }

    public Solution getOptimalSolution()
    {
        if (!SimulationSettings.getDEVELOPMENT_MODE())
        {
            return optimalSolution;
        }

        if (optimalSolution.getScore() < 2000)
        {
            System.out.println("Optimal Solution : " + optimalSolution.toString());

            System.exit(0);
        }
        else if (currentNeighbours.isEmpty())
        {
            generateNewNeighbours();
        }
        

        currentSolution = currentNeighbours.get(0);

        currentNeighbours.remove(0);
        
        
        return currentSolution;
    }

    private void generateNewNeighbours()
    {
        List<Solution> newNeighbours = new ArrayList<>();

        for (int i = 0; i < AMOUNT_OF_NEIGHBOURS; i++)
        {
            Solution neighbour = new Solution(generateNeighbourVelocity(optimalSolution.getVelocity()), generateNeighbourForce(optimalSolution.getThrusterForce()));

            newNeighbours.add(neighbour);
        }

        currentNeighbours = newNeighbours;
    }

    private Vector3 generateNeighbourVelocity(Vector3 initialVelocity)
    {
        Vector3 neighbourVelocity = new Vector3(initialVelocity.getX() + (Math.random() * MAX_NEIGHBOUR_VELOCITY_DIFFERENCE) - MIN_NEIGHBOUR_VELOCITY_DIFFERENCE, initialVelocity.getY() + (Math.random() * MAX_NEIGHBOUR_FORCE_DIFFERENCE) - MIN_NEIGHBOUR_VELOCITY_DIFFERENCE, initialVelocity.getZ() + (Math.random() * MAX_NEIGHBOUR_VELOCITY_DIFFERENCE) - MIN_NEIGHBOUR_VELOCITY_DIFFERENCE);

        return neighbourVelocity;
    }

    private double generateNeighbourForce(double initialForce)
    {
        double neighbourForce = initialForce + (Math.random() * MAX_NEIGHBOUR_FORCE_DIFFERENCE) - MIN_NEIGHBOUR_FORCE_DIFFERENCE;

        return neighbourForce;
    }

    @Override
    public void update()
    {
        double newScore = PhysicsObjectData.getInstance().getRocketShipDistanceToTitan();

        if (newScore < currentSolution.getScore())
        {
            currentSolution.setScore(newScore);
        }
    }

    @Override
    public void reset()
    {
        if (currentSolution.getScore() < optimalSolution.getScore())
        {
            optimalSolution = currentSolution;


            System.out.println("-----------------------------------------------------------");

            System.out.println("Optimal Solution : " + optimalSolution.toString());

            System.out.println("-----------------------------------------------------------");
        }
        else
        {
            System.out.println("Optimal Solution : " + optimalSolution.toString() + "\n" + "Current Solution : " + currentSolution.toString() +"\n");
        }
    }
}
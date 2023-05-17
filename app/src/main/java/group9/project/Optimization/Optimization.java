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

    private List<Solution> currentNeighbours = new ArrayList<>();

    private Solution optimalSolution;

    private Solution currentSolution;

    private Optimization()
    {
        currentSolution = new Solution(new Vector3(51.73103664964552, -37.65773832186801, -2.2727731130412083), 0);

        optimalSolution = currentSolution;
    }

    public Solution getOptimalSolution()
    {
        if (!SimulationSettings.getDEVELOPMENT_MODE())
        {
            return optimalSolution;
        }

        if (optimalSolution.getScore() < 2000)
        {
            System.out.println("Optimal Score : " + optimalSolution.getScore() + " | " + "Optimal Solution : " + optimalSolution.getVelocity());

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
        Vector3 neighbourVelocity = new Vector3(initialVelocity.getX() + (Math.random() * 4) - 2, initialVelocity.getY() + (Math.random() * 4) - 2, initialVelocity.getZ() + (Math.random() * 4) - 2);

        return neighbourVelocity;
    }

    private double generateNeighbourForce(double initialForce)
    {
        double neighbourForce = initialForce + Math.random() * 2;

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
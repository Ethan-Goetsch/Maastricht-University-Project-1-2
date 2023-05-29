package group9.project.Optimization;

import java.util.ArrayList;
import java.util.List;

import group9.project.Utility.RandomGenerator;
import group9.project.Utility.Interfaces.IResetable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Vector3;

public abstract class LaunchOptimization implements IUpdateable, IResetable
{
    protected final static int AMOUNT_OF_NEIGHBOURS = 10;


    protected final static double BASE_NEIGHBOUR_VELOCITY_DIFFERENCE = 1;

    
    protected boolean canStartOptimizing;


    protected List<Solution> currentNeighbours; 

    protected Solution currentSolution;

    protected boolean getCanStartOptimizing()
    {
        return canStartOptimizing;
    }

    protected LaunchOptimization()
    {
        currentNeighbours = new ArrayList<>();
    }

    /**
     * @return true if the Launch Optimization matches the Development Mode, false otherwise
     */
    public abstract boolean getIsOptimizationDevelopmentMode();

    /**
     * @return the current optimal solution
     */
    protected abstract Solution getOptimalSolution();

    /**
     * Sets the Optimal Solution to the inputted solution
     * 
     * @param newSolution the new optimal solution
     */
    protected abstract void setOptimalSolution(Solution newSolution);

    /**
     * @return true if the Rocket Ship has reached the optimal solution
     */
    protected abstract boolean getIsOptimalCondition();

    /**
     * @return the score of the current solution
     */
    protected abstract double getScoreCondition();

    /**
     * If in development mode will generate new solutions to test, otherwise will return the optimal solution
     * 
     * @return the optimal solution
     */
    public Solution generateOptimalSolution()
    {
        setCanStartOptimzing(true);

        if (!getIsOptimizationDevelopmentMode())
        {
            return getOptimalSolution();
        }

        if (getIsOptimalCondition())
        {
            System.out.println("----------------------------End----------------------------");

            System.out.println("Optimal Solution : " + getOptimalSolution().toString());

            System.out.println("----------------------------End----------------------------");

            System.exit(0);
        }
        if (getOptimalSolution().getScore() == Integer.MAX_VALUE)
        {
            return getOptimalSolution();
        }
        else if (currentNeighbours.isEmpty())
        {
            currentNeighbours = generateNewNeighbours();
        }
        

        currentSolution = currentNeighbours.get(0);

        currentNeighbours.remove(0);
        
        
        return currentSolution;
    }

    /**
     * Generates a List<Solution> of new neighbours from the current optimal solution
     * 
     * @return list of new neighbours
     */
    private List<Solution> generateNewNeighbours()
    {
        List<Solution> newNeighbours = new ArrayList<>();

        for (int i = 0; i < AMOUNT_OF_NEIGHBOURS; i++)
        {
            Vector3 neighbourVelocity = generateNeighbourVelocity(getOptimalSolution().getVelocity(), getOptimalSolution().getScore());

            Solution neighbour = new Solution(neighbourVelocity);

            newNeighbours.add(neighbour);
        }

        return newNeighbours;
    }

    /**
     * Generates a new velocity for a new neighbour
     * 
     * @param initialVelocity the initialVelocity of the current optimal solution
     * @param score the score of the current solution
     * 
     * @return the initial velocity of the new neighbour
     */
    private Vector3 generateNeighbourVelocity(Vector3 initialVelocity, double score)
    {
        Vector3 neighbourVelocity = initialVelocity.add(getRandomVelocity(score));

        return neighbourVelocity;
    }

    /**
     * Generates a random vector between -BASE_NEIGHBOUR_VELOCITY_DIFFERENCE and BASE_NEIGHBOUR_VELOCITY_DIFFERENCE
     * 
     * @param score the score of the current solution
     * 
     * @return a new random vector
     */
    private Vector3 getRandomVelocity(double score)
    {
        double maxNeighbourDifference = BASE_NEIGHBOUR_VELOCITY_DIFFERENCE;

        double minNeighbourDifference = -BASE_NEIGHBOUR_VELOCITY_DIFFERENCE;


        return new Vector3(RandomGenerator.generateRandom(minNeighbourDifference, maxNeighbourDifference), RandomGenerator.generateRandom(minNeighbourDifference, maxNeighbourDifference), RandomGenerator.generateRandom(minNeighbourDifference, maxNeighbourDifference));
    }

    /**
     * Sets canStartOptimizing
     * 
     * @param value determines whether the Launch Optimization can start optimizing or not
     */
    protected void setCanStartOptimzing(boolean value)
    {
        canStartOptimizing = value;
    }

    /**
     * Updates the current solution
     * Gets the current solution's score and updates the current solution if it is less than its current score
     */
    @Override
    public void update()
    {
        if (!getIsOptimizationDevelopmentMode())
        {
            return;
        }

        double newScore = getScoreCondition();

        if (newScore < currentSolution.getScore())
        {
            currentSolution.setScore(newScore);
        }
    }

    /**
     * Resets the Launch Optimization
     * Sets the optimal solution equal to the current solution if the current solution's score is less than the optimal solution's score
     */
    @Override
    public void reset()
    {
        setCanStartOptimzing(false);

        if (currentSolution.getScore() < getOptimalSolution().getScore())
        {
            setOptimalSolution(currentSolution);


            System.out.println("-----------------------------------------------------------");

            System.out.println("Optimal Solution : " + getOptimalSolution().toString());

            System.out.println("-----------------------------------------------------------");
        }
        else
        {
            System.out.println("\n" + "Optimal Solution : " + getOptimalSolution().toString() + "\n" + "Current Solution : " + currentSolution.toString());
        }
    } 
}
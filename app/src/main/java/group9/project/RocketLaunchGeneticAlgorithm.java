package group9.project;

import javafx.scene.paint.Color;

public class RocketLaunchGeneticAlgorithm {
    private double mutationRate = 0.666;
    private double mutationSizeX = 20;
    private double mutationSizeY = 20;
    private double mutationSizeZ = 20;
    private final int populationSize;
    private RocketShipGA[] population;
    RocketShipGA bestRocket = null;
    public int generation = 0;

    public RocketLaunchGeneticAlgorithm(double mutationRate, int populationSize)
    {
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;

        population = createPopulation(populationSize);
    }

    public void nextGeneration()
    {
        RocketShipGA[] newPopulation = new RocketShipGA[populationSize];
        int newPopulationSize = 0;

        RocketShipGA parent = chooseParent(population);

        setMutationRates(parent); // dynamic mutation rates

        newPopulation[0] = parent;

        for (int i = 1; i < populationSize; i++) {

            if (newPopulationSize >= populationSize)
            {
                break;
            }

            newPopulation[i] = mutate(parent); // clone + mutate parent
            newPopulationSize++;
        }

        // remove old population from physics engine
        for (RocketShipGA rocket : population) {
            PhysicsEngine.getInstance().getPhysicsObjectsToUpdate().remove(rocket);
        }

        population = newPopulation;
        generation++;

    }
    
    /*
     * Sets independent mutation rates for x, y and z components of the initial velocity vector of the rocket.
     */
    private void setMutationRates(RocketShipGA bestRocket)
    {
        double closestX = bestRocket.getClosestX();
        double closestY = bestRocket.getClosestY();
        double closestZ = bestRocket.getClosestZ();

        if (closestX < 4.5e8)
        {
            mutationSizeX = 4;
        }
        if (closestX < 2e8)
        {
            mutationSizeX = 3;
        }
        if (closestX < 8e7)
        {
            mutationSizeX = 2;
        }
        if (closestX < 2e7)
        {
            mutationSizeX = 1;
        }
        if (closestX < 1e6)
        {
            mutationSizeX = 0.01;
        }
        if (closestX < 1e5)
        {
            mutationSizeX = 0.001;
        }
        if (closestX < 1e4)
        {
            mutationSizeX = 0.0001;
        }
        if (closestX < 100)
        {
            mutationSizeX = 0.00001;
        }
        if (closestX < 10)
        {
            mutationSizeX = 0.000001;
        }

        if (closestY < 4.5e8)
        {
            mutationSizeY = 4;
        }
        if (closestY < 2e8)
        {
            mutationSizeY = 3;
        }
        if (closestY < 8e7)
        {
            mutationSizeY = 2;
        }
        if (closestY < 2e7)
        {
            mutationSizeY = 1;
        }
        if (closestY < 1e6)
        {
            mutationSizeY = 0.01;
        }
        if (closestY < 1e5)
        {
            mutationSizeY = 0.001;
        }
        if (closestY < 1e4)
        {
            mutationSizeY = 0.0001;
        }
        if (closestY < 100)
        {
            mutationSizeY = 0.00001;
        }
        if (closestY < 10)
        {
            mutationSizeY = 0.000001;
        }

        if (closestZ < 4.5e8)
        {
            mutationSizeZ = 4;
        }
        if (closestZ < 2e8)
        {
            mutationSizeZ = 3;
        }
        if (closestZ < 8e7)
        {
            mutationSizeZ = 2;
        }
        if (closestZ < 2e7)
        {
            mutationSizeZ = 1;
        }
        if (closestZ < 1e6)
        {
            mutationSizeZ = 0.01;
        }
        if (closestZ < 1e5)
        {
            mutationSizeZ = 0.001;
        }
        if (closestZ < 1e4)
        {
            mutationSizeZ = 0.0001;
        }
        if (closestZ < 100)
        {
            mutationSizeZ = 0.00001;
        }
        if (closestZ < 10)
        {
            mutationSizeZ = 0.000001;
        }


    }

    public RocketShipGA mutate(RocketShipGA rocket)
    {

        double rNum = Math.random();
        double x = rocket.getStartingVelocity().getX();
        double y = rocket.getStartingVelocity().getY();
        double z = rocket.getStartingVelocity().getZ();

        if (rNum < mutationRate)
        {
            x = rocket.getStartingVelocity().getX() + (Math.random()*(2*mutationSizeX)-mutationSizeX); // add a number in the range of [-mutationSize, +mutationSize]
        }

        rNum = Math.random();
        if (rNum < mutationRate)
        {
            y = rocket.getStartingVelocity().getY() - (Math.random()*(2*mutationSizeY)-mutationSizeY);
        }

        rNum = Math.random();
        if (rNum < mutationRate)
        {
            z = rocket.getStartingVelocity().getZ() - (Math.random()*(2*mutationSizeZ)-mutationSizeZ);
        }
        
        Vector3 velocity = new Vector3(x, y, z);
        if (velocity.getMagnitude() > 60)
        {
            velocity = velocity.divideBy(velocity.getMagnitude()).multiplyBy(60); // make sure initial velocity is not over 60km/s in magnitude
        }


        //setStartingVelocity(new Vector3(x, y, z));
        RocketShipGA child = new RocketShipGA(rocket.getStartingPosition(), velocity, rocket.getMass(), PhysicsObjectType.Rocket, rocket.getWidth(), rocket.getHeight(), rocket.getColour());
        return child;
    }

    public RocketShipGA getBestRocket()
    {
        return bestRocket;
    }

    public RocketShipGA chooseParent(RocketShipGA[] population)
    {
        RocketShipGA bestRocket = population[0];
        double closestDistance = Double.MAX_VALUE;
        for (RocketShipGA rocket : population) {
            // store rocket that reached closest to titan
            if (rocket.getClosestDistance() < closestDistance)
            {
                closestDistance = rocket.getClosestDistance();
                bestRocket = rocket;
            }
        }
        this.bestRocket = bestRocket;
        return bestRocket;
    }

    public RocketShipGA[] createPopulation(int populationSize)
    {
        RocketShipGA[] population = new RocketShipGA[populationSize];
        population[0] = new RocketShipGA(new Vector3(-148186906.893642, -27823158.5715694 + 6370, 33746.8987977113), new Vector3(43.055263066324734, -41.35587532316244, -3.3992847916377094), 50000, PhysicsObjectType.Rocket, 10, 10, Color.BLUE);
        for (int i = 1; i < populationSize; i++) {
            Vector3 velocity = new Vector3(Math.random()*120-60, Math.random()*120-60, Math.random()*120-60);
            if (velocity.getMagnitude() > 60)
            {
                velocity = velocity.divideBy(velocity.getMagnitude()).multiplyBy(60);
            }
            population[i] = new RocketShipGA(new Vector3(-148186906.893642, -27823158.5715694 + 6370, 33746.8987977113), velocity, 50000, PhysicsObjectType.Rocket, 10, 10, Color.BLUE);
        }

        return population;
    }
}

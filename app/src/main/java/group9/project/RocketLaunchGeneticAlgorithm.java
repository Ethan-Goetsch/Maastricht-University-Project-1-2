package group9.project;

import java.util.stream.IntStream;

import javafx.scene.paint.Color;

public class RocketLaunchGeneticAlgorithm {
    private final double mutationRate;
    private double mutationSizeX = 20;
    private double mutationSizeY = 20;
    private double mutationSizeZ = 20;
    private final int populationSize;
    private RocketShipGA[] population;
    RocketShipGA bestRocket = null;

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

        // create array of fitness scores
        int[] fitnessScores = new int[populationSize];
        for (int i = 0; i < population.length; i++) {
            fitnessScores[i] = getFitnessScore(population[i]);
        }

        RocketShipGA parent = chooseParent(population, fitnessScores);
        setMutationRates(parent);
        newPopulation[0] = parent;
        int randomRocket = (int)(Math.random() * populationSize);
        for (int i = 1; i < populationSize; i++) {
            if (newPopulationSize >= populationSize)
            {
                break;
            }
            newPopulation[i] = mutate(parent, i == randomRocket);
            if (newPopulation[i].getStartingVelocity().getMagnitude()>60)
            {
                System.out.println(newPopulation[i].getStartingVelocity().getMagnitude());
            }
            newPopulationSize++;
        }

        for (RocketShipGA rocket : population) {
            PhysicsEngine.getInstance().getPhysicsObjectsToUpdate().remove(rocket);
        }

        population = newPopulation;

    }
    
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
            mutationSizeX = 0.1;
        }
        if (closestX < 1e5)
        {
            mutationSizeX = 0.01;
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
            mutationSizeY = 0.1;
        }
        if (closestY < 1e5)
        {
            mutationSizeY = 0.01;
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
            mutationSizeZ = 0.1;
        }
        if (closestZ < 1e5)
        {
            mutationSizeZ = 0.01;
        }


    }

    private int getFitnessScore(RocketShipGA rocket)
    {
        if (rocket.getStartingVelocity().getMagnitude()>60)
        {
            return 0;
        }

        return 1;
    }

    public RocketShipGA mutate(RocketShipGA rocket, boolean randomRocket)
    {
        double rNum = Math.random();
        double x = rocket.getStartingVelocity().getX();
        double y = rocket.getStartingVelocity().getY();
        double z = rocket.getStartingVelocity().getZ();
        if (rNum > 0.3334)
        {
            if (randomRocket)
            {
                x = rocket.getStartingVelocity().getX() - Math.random()*15 + 1;
            }
            else 
            {
                x = rocket.getStartingVelocity().getX() + Math.random()*(2*mutationSizeX)-mutationSizeX;
            }
        }

        rNum = Math.random();
        if (rNum > 0.3334)
        {
            if (randomRocket)
            {
                y = rocket.getStartingVelocity().getY() - Math.random()*15 + 1;
            }
            else 
            {
                y = rocket.getStartingVelocity().getY() - Math.random()*(2*mutationSizeY)-mutationSizeY;
            }
        }

        rNum = Math.random();
        if (rNum > 0.3334)
        {
            if (randomRocket)
            {
                z = rocket.getStartingVelocity().getZ() - Math.random()*15 + 1;
            }
            else 
            {
                z = rocket.getStartingVelocity().getZ() - Math.random()*(2*mutationSizeZ)-mutationSizeZ;
            }
        }
        
        Vector3 velocity = new Vector3(x, y, z);
        if (velocity.getMagnitude() > 60)
        {
            velocity = velocity.divideBy(velocity.getMagnitude()).multiplyBy(60);
        }


        //setStartingVelocity(new Vector3(x, y, z));
        RocketShipGA child = new RocketShipGA(rocket.getStartingPosition(), velocity, rocket.getMass(), PhysicsObjectType.Rocket, rocket.getWidth(), rocket.getHeight(), rocket.getColour());
        return child;
    }

    public RocketShipGA getBestRocket()
    {
        return bestRocket;
    }

    public RocketShipGA chooseParent(RocketShipGA[] population, int[] fitnessScores)
    {
        RocketShipGA bestRocket = population[0];
        double closestDistance = Double.MAX_VALUE;
        for (RocketShipGA rocket : population) {
            if (rocket.getClosestDistance() < closestDistance)
            {
                closestDistance = rocket.getClosestDistance();
                bestRocket = rocket;
            }
        }
        this.bestRocket = bestRocket;
        return bestRocket;
        /* 
        int p = (int)(Math.random() * IntStream.of(fitnessScores).sum());
        for (int i = 0; i < fitnessScores.length; i++) {
            p -= fitnessScores[i];
            if (p <= 0)
            {
                return population[i];
            }
        }
        return population[population.length-1];
        */
        /* 
        def select_parent(population, fitness_values):
            p = random.randint(0, sum(fitness_values))
            for i, f in enumerate(fitness_values):
                p -= f
                if p <= 0:
                    break
            return i
            */
    }

    public RocketShipGA[] createPopulation(int populationSize)
    {
        RocketShipGA[] population = new RocketShipGA[populationSize];
        //population[0] = new RocketShipGA(new Vector3(-148186906.893642, -27823158.5715694 + 6370, 33746.8987977113), new Vector3(43.07164438203265, -41.31788889917668, -3.8464189997186904), 50000, PhysicsObjectType.Rocket, 10, 10, Color.ORANGE);
        for (int i = 0; i < populationSize; i++) {
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

package group9.project;

import java.util.ArrayList;

public class PhysicsEngine implements IStartable, IUpdateable
{
    //#region Singleton
    private static PhysicsEngine instance;

    public static PhysicsEngine getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsEngine();
        }

        return instance;
    }
    //#endregion

    private static final double STEP_TIME = 100;

    private ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public static double getSimulationStepTime()
    {
        return STEP_TIME * SimulationSettings.getSimulationSpeed();
    }

    public ArrayList<PhysicsObject> getPhysicsObjectsToUpdate()
    {
        return physicsObjectsToUpdate;
    }

    public PhysicsEngine()
    {
        instance = this;

        physicsObjectsToUpdate = new ArrayList<>();
    }

    public void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        if (!physicsObjectsToUpdate.contains(physicsObject))
        {
            physicsObjectsToUpdate.add(physicsObject);
        }
    }

    @Override
    public void start()
    {
        
    }

    @Override
    public void update()
    {
        if (SimulationSettings.getIsSimulationPaused())
        {
            return;
        }

        updateForces();

        updateObjects();

        updateTimer();
    }

    private void updateForces()
    {
        for (PhysicsObject physicsBodyOne : getPhysicsObjectsToUpdate())
        {
            Vector3 physicsBodyOneForce = new Vector3();

            for (PhysicsObject physicsBodyTwo : getPhysicsObjectsToUpdate()) 
            {
                if (physicsBodyOne.equals(physicsBodyTwo))
                {
                    continue;
                }
                else if (physicsBodyTwo.physicsObjectType == PhysicsObjectType.Rocket)
                {
                    continue;
                }

                double productOfMassAndGravity = SimulationSettings.GRAVITY * physicsBodyOne.getMass() * physicsBodyTwo.getMass();


                Vector3 positionDifference = physicsBodyOne.getPosition().subtract(physicsBodyTwo.getPosition());

                double distance = Mathematics.getDistance(physicsBodyOne.getPosition(), physicsBodyTwo.getPosition());

                distance = Math.pow(distance, 3);


                Vector3 force = positionDifference.multiplyBy(productOfMassAndGravity);

                force = force.divideBy(distance);


                force = force.multiplyBy(-1);

                physicsBodyOneForce = physicsBodyOneForce.add(force);
            }

            physicsBodyOne.setForce(physicsBodyOneForce);

            physicsBodyOne.setAcceleration(physicsBodyOne.getForce().divideBy(physicsBodyOne.getMass()));
        }
    }

    private void updateObjects()
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }

    private void updateTimer()
    {
        SimulationSettings.updateSimulationTime(getSimulationStepTime());
    }
}
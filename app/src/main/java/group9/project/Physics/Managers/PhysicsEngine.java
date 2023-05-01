package group9.project.Physics.Managers;

import java.util.ArrayList;

import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

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

    private ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public ArrayList<PhysicsObject> getPhysicsObjectsToUpdate()
    {
        return physicsObjectsToUpdate;
    }

    private PhysicsEngine()
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
        else if (PhysicsSettings.getMaxUniverseTime() != 0 && SimulationSettings.getSimulationTime() > PhysicsSettings.getMaxUniverseTime())
        {
            return;
        }
        
        updateForces();

        updateObjects();
        

        SimulationSettings.updateSimulationTime(PhysicsSettings.getStepTime());
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
                else if (physicsBodyTwo.getPhysicsObjectType() == PhysicsObjectType.Rocket)
                {
                    continue;
                }

                double productOfMassAndGravity = PhysicsSettings.getGravity() * physicsBodyOne.getMass() * physicsBodyTwo.getMass();


                Vector3 positionDifference = physicsBodyOne.getPosition().subtract(physicsBodyTwo.getPosition());

                double distance = Mathematics.calculateDistance(physicsBodyOne.getPosition(), physicsBodyTwo.getPosition());

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
}
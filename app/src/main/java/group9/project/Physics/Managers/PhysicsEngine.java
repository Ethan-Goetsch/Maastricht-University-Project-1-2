package group9.project.Physics.Managers;

import java.util.ArrayList;
import java.util.List;

import group9.project.Physics.PhysicsStateData;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.EulerSolver;
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

    private PhysicsObject[] physicsObjectsToUpdate;

    private PhysicsStateData[] physicsStateData;

    public PhysicsObject[] getPhysicsObjectsToUpdate()
    {
        return physicsObjectsToUpdate;
    }

    private PhysicsEngine()
    {
        instance = this;

        physicsObjectsToUpdate = new PhysicsObject[12];

        physicsStateData = new PhysicsStateData[0];
    }

    public void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        physicsObjectsToUpdate[physicsObject.getPhysicsObjectType().getValue()] = physicsObject; 
    }

    @Override
    public void start()
    {
        physicsStateData = new PhysicsStateData[physicsObjectsToUpdate.length];

        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.start();    
        }
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
        for (int i = 0; i < physicsObjectsToUpdate.length; i++)
        {
            physicsStateData[i] = new PhysicsStateData(physicsObjectsToUpdate[i]);
        }

        for (int i = 0; i < physicsObjectsToUpdate.length; i++)
        {
            physicsObjectsToUpdate[i].update();
        }
    }

    private void updateTimer()
    {
        SimulationSettings.updateSimulationTime(PhysicsSettings.getStepTime());
    }

    public Vector3 calculateAccelerationAtPoint(double h, PhysicsObjectType physicsObjectType)
    {
        DifferentialSolver differentialSolver = new EulerSolver();

        movePhysicsObjectsInTime(differentialSolver, h);

        updateForces();

        return physicsObjectsToUpdate[(physicsObjectType.getValue())].getAcceleration();
    }

    private void movePhysicsObjectsInTime(DifferentialSolver differentialSolver, double h)
    {
        for (int i = 0; i < physicsObjectsToUpdate.length; i++)
        {
            PhysicsObject physicsObject = physicsObjectsToUpdate[i];

            physicsObject.setPosition(differentialSolver.solveEquation(physicsStateData[i].getCurrentPosition(), physicsStateData[i].getCurrentVelocity(), physicsStateData[i].getCurrentAcceleration(), h, physicsObject.getPhysicsObjectType())[0]);
        }
    }
}
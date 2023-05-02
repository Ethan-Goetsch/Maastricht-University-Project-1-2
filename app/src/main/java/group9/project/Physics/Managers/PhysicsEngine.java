package group9.project.Physics.Managers;

import java.util.ArrayList;

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
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }
    }

    private void updateTimer()
    {
        SimulationSettings.updateSimulationTime(PhysicsSettings.getStepTime());
    }

    public Vector3 calculateAccelerationAtPoint(double h, PhysicsObjectType physicsObjectType)
    {
        DifferentialSolver differentialSolver = new EulerSolver();

        Vector3 accelerationAtPoint;
        

        Vector3[] oldPositions = new Vector3[physicsObjectsToUpdate.size()];

        Vector3[] oldForces = new Vector3[physicsObjectsToUpdate.size()];

        Vector3[] oldAccelerations = new Vector3[physicsObjectsToUpdate.size()];


        movePhysicsObjectsInTime(differentialSolver, h, oldPositions, oldForces, oldAccelerations);

        updateForces();

        accelerationAtPoint = getObjectAcceleration(physicsObjectType);
        
        resetPhysicsObjectData(oldPositions, oldForces, oldAccelerations);


        return accelerationAtPoint;
    }

    private void movePhysicsObjectsInTime(DifferentialSolver differentialSolver, double h, Vector3[] oldPositions, Vector3[] oldForce, Vector3[] oldAcceleration)
    {
        for (int i = 0; i < physicsObjectsToUpdate.size(); i++)
        {
            PhysicsObject physicsObject = physicsObjectsToUpdate.get(i);


            oldPositions[i] = physicsObject.getPosition();

            oldForce[i] = physicsObject.getForce();

            oldAcceleration[i] = physicsObject.getAcceleration();


            physicsObject.setPosition(differentialSolver.solveEquation(physicsObject.getPosition(), physicsObject.getVelocity(), physicsObject.getAcceleration(), h, physicsObject.getPhysicsObjectType())[0]);
        }
    }

    private Vector3 getObjectAcceleration(PhysicsObjectType physicsObjectType)
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            if (physicsObject.getPhysicsObjectType() == physicsObjectType)
            {
                return physicsObject.getAcceleration();
            }
        }

        return null;
    }

    private void resetPhysicsObjectData(Vector3[] oldPositions, Vector3[] oldForce, Vector3[] oldAcceleration)
    {
        for (int i = 0; i < physicsObjectsToUpdate.size(); i++)
        {
            PhysicsObject physicsObject = physicsObjectsToUpdate.get(i);


            physicsObject.setPosition(oldPositions[i]);

            physicsObject.setForce(oldForce[i]);

            physicsObject.setAcceleration(oldAcceleration[i]);
        }
    }
}
package group9.project.Physics.Managers;

import group9.project.Physics.PhysicsStateData;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.EulerSolver;
import group9.project.Utility.Interfaces.IResetable;
import group9.project.Utility.Interfaces.IStartable;
import group9.project.Utility.Interfaces.IUpdateable;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

public class PhysicsEngine implements IStartable, IUpdateable, IResetable
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

    private PhysicsObject[] physicsObjects;

    private PhysicsStateData[] physicsStateData;

    private PhysicsEngine()
    {
        instance = this;

        physicsObjects = new PhysicsObject[12];

        physicsStateData = new PhysicsStateData[0];
    }

    public void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        physicsObjects[physicsObject.getPhysicsObjectType().getValue()] = physicsObject; 
    }

    @Override
    public void start()
    {
        physicsStateData = new PhysicsStateData[physicsObjects.length];

        for (int i = 0; i < physicsObjects.length; i++)
        {
            physicsStateData[i] = new PhysicsStateData(physicsObjects[i]);
        }

        for (PhysicsObject physicsObject : physicsObjects)
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

    @Override
    public void reset()
    {
        for (int i = 0; i < physicsObjects.length; i++)
        {
            physicsObjects[i] = null;
        }
    }

    public PhysicsObject[] getPhysicsObjects()
    {
        return physicsObjects;
    }

    private void updateForces()
    {
        for (PhysicsObject physicsObject : physicsObjects)
        {
            physicsObject.setForce(calculateForce(physicsObject));
        }
    }

    private Vector3 calculateForce(PhysicsObject physicsObject)
    {
        Vector3 physicsObjectForce = new Vector3();

        for (PhysicsObject physicsObjectTwo : physicsObjects) 
        {
            if (physicsObject.equals(physicsObjectTwo))
            {
                continue;
            }
            else if (physicsObjectTwo.getPhysicsObjectType() == PhysicsObjectType.Rocket)
            {
                continue;
            }

            double productOfMassAndGravity = PhysicsSettings.getGravity() * physicsObject.getMass() * physicsObjectTwo.getMass();


            Vector3 positionDifference = physicsObject.getPosition().subtract(physicsObjectTwo.getPosition());

            double distance = Mathematics.calculateDistance(physicsObject.getPosition(), physicsObjectTwo.getPosition());

            distance = Math.pow(distance, 3);


            Vector3 force = positionDifference.multiplyBy(productOfMassAndGravity);

            force = force.divideBy(distance);


            force = force.multiplyBy(-1);

            physicsObjectForce = physicsObjectForce.add(force);
        }

        return physicsObjectForce;
    }

    private void updateObjects()
    {
        for (int i = 0; i < physicsObjects.length; i++)
        {
            physicsStateData[i].update();
        }

        for (int i = 0; i < physicsObjects.length; i++)
        {
            physicsObjects[i].update();
        }
    }

    private void updateTimer()
    {
        SimulationSettings.updateSimulationTime(PhysicsSettings.getStepTime());
    }

    public Vector3 calculateAcceleration(double h, PhysicsObjectType physicsObjectType)
    {
        DifferentialSolver differentialSolver = new EulerSolver();

        movePhysicsObjectsInTime(h, differentialSolver);

        return calculateForce(physicsObjects[physicsObjectType.getValue()]).divideBy(physicsObjects[physicsObjectType.getValue()].getMass());
    }

    private void movePhysicsObjectsInTime(double h, DifferentialSolver differentialSolver)
    {
        for (int i = 0; i < physicsObjects.length; i++)
        {
            Vector3[] newState = differentialSolver.solveEquation(physicsStateData[i].getCurrentPosition(), physicsStateData[i].getCurrentVelocity(), physicsStateData[i].getCurrentAcceleration(), h, physicsStateData[i].getPhysicsObjectType());

            physicsObjects[i].setPosition(newState[0]);
        }
    }
}
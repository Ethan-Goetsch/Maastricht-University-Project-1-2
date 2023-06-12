package group9.project.Physics.Managers;

import group9.project.Physics.PhysicsStateData;
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.Physics.Objects.PhysicsObjectType;
import group9.project.Settings.PhysicsSettings;
import group9.project.Settings.SimulationSettings;
import group9.project.Solvers.DifferentialSolver;
import group9.project.Solvers.EulerSolver;
import group9.project.Solvers.RotationSolver;
import group9.project.Solvers.RungeKuttaFourthSolver;
import group9.project.Utility.Interfaces.INumericalMethod;
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

    /**
     * Adds a Physics Object to the Array of Physics Objects to be updated in the Physics Engine. Adds the object to the index at the Physics Object's Type Value
     * 
     * @param physicsObject the Physics Object to add to the Physics Engine's list of Physics Objects
     */
    public void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        physicsObjects[physicsObject.getPhysicsObjectType().getValue()] = physicsObject; 
    }

    /**
     * Initializes the Physics State Data with the Physics Object's Initial Values and Starts each Physics Object
     */
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

    /**
     * Updates the Physics Engine
     */
    @Override
    public void update()
    {       
        updateForces();

        updateObjects();

        updateTimer();
    }

    /**
     * Resets the Physics Engine
     */
    @Override
    public void reset()
    {
        for (int i = 0; i < physicsObjects.length; i++)
        {
            physicsObjects[i] = null;
        }
    }

    /**
     * Updates the forces on each Physics Object
     */
    private void updateForces()
    {
        for (PhysicsObject physicsObject : physicsObjects)
        {
            physicsObject.setForce(calculateForce(physicsObject));
        }
    }

    /**
     * Calculates the force on a Physics Object
     * 
     * @param physicsObject the Physics Object to calculate the force on
     * 
     * @returns the sum of the forces on a Physics Object based on the Force Equation given in the Manual.
     */
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
  
    /**
     * Records each Physics Objects Data and stores their current values in their Physics State Data and then updates each Physics Object
     */
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

    /**
     * Updates the Simulation Timer by the Step Size of the Physics Settings
     */
    private void updateTimer()
    {
        SimulationSettings.updateSimulationTime(PhysicsSettings.getStepSize());
    }

    /**
     * Calculates the acceleration of a Physics Object by moving all the objects forward in time and recalculating their forces and acceleration based on their new position
     * 
     * @param h the Step Size used in the Diffferential Solver to move each object in time
     * @param physicsObjectType the Physics Object to calculate the acceleration for
     * 
     * @return the acceleration of the Physics Object at a point in time
     */
    public Vector3 calculateAcceleration(double h, PhysicsObjectType physicsObjectType)
    {
        if (h == 0)
        {
            return physicsObjects[physicsObjectType.getValue()].getAcceleration();
        }

        DifferentialSolver differentialSolver = new EulerSolver();

        movePhysicsObjectsInTime(h, differentialSolver);

        return calculateForce(physicsObjects[physicsObjectType.getValue()]).divideBy(physicsObjects[physicsObjectType.getValue()].getMass());
    }

    /**
     * Moves each Physics Object forward in time. Uses the Physics State Data in the Differential Solver's calculations
     * 
     * @param h the Step Size used in the Euler Solver to move each object in time
     * @param differentialSolver the Differential Solver used to approximate each Physics Objects new position
     */
    private void movePhysicsObjectsInTime(double h, DifferentialSolver differentialSolver)
    {
        for (int i = 0; i < physicsObjects.length; i++)
        {
            Vector3[] newState = differentialSolver.solvePhysicsEquation(physicsStateData[i].getCurrentPosition(), physicsStateData[i].getCurrentVelocity(), physicsStateData[i].getCurrentAcceleration(), h, physicsStateData[i].getPhysicsObjectType());

            physicsObjects[i].setPosition(newState[0]);
        }
    }

    public Vector3[] calculateMotion(Vector3 position, Vector3 velocity, double theta, double u, double v, double h)
    {
        Vector3[] states = new Vector3[2];

        INumericalMethod<Vector3> positionSolver = new RungeKuttaFourthSolver();


        double xAcceleration = u * Math.sin(theta);

        double yAcceleration = u * Math.cos(theta) - PhysicsSettings.getTitansGravity();


        Vector3 acceleration = new Vector3(xAcceleration, yAcceleration, 0);

        Vector3 newPosition = positionSolver.solveNumericalEquation(position, (t, w) -> velocity, h, 0);

        Vector3 newVelocity = positionSolver.solveNumericalEquation(velocity, (t, w) -> acceleration, h, 0);


        states[0] = newPosition;

        states[1] = newVelocity;


        return states;
    }

    public Double[] calculateRotation(double rotation, double rotationVelocity, double v, double h)
    {
        Double[] states = new Double[2];

        INumericalMethod<Double> rotationSolver = new RotationSolver();


        double rotationAcceleration = v;

        double newRotation = rotationSolver.solveNumericalEquation(rotation, (t, w) -> rotationVelocity, h, 0);

        double newRotationVelocity = rotationSolver.solveNumericalEquation(rotationVelocity, (t, w) -> rotationAcceleration, h, 0);


        states[0] = newRotation;

        states[1] = newRotationVelocity;


        return states;
    }
}
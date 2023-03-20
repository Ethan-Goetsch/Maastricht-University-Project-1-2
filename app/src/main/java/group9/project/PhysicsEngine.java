package group9.project;

import java.util.ArrayList;

public class PhysicsEngine
{
    public static final double STEP_TIME = 0.001;
    public static final double G = 7.6743e-20;

    private static ArrayList<PhysicsObject> physicsObjectsToUpdate = new ArrayList<>();

    public PhysicsEngine()
    {
        physicsObjectsToUpdate = new ArrayList<>();
    }

    public static void addPhysicsObjectToUpdate(PhysicsObject physicsObject)
    {
        if (!physicsObjectsToUpdate.contains(physicsObject))
        {
            physicsObjectsToUpdate.add(physicsObject);
        }
    }

    public static void updateForces() {
        // calculate and apply forces to all physics objects

        // gravity:

        for (PhysicsObject body : physicsObjectsToUpdate) {

            for (PhysicsObject body2 : physicsObjectsToUpdate) {
                if (body.equals(body2)) continue; 

                    // Calculate the distance vector between the two objects
                    Vector3 r = body2.getPosition().subtract(body.getPosition());

                    // Calculate the magnitude of the gravitational force using the universal law of gravitation
                    double magF = G * body.getMass() * body2.getMass() / Math.pow(r.getMagnitude(), 2);

                    // Calculate the direction of the gravitational force
                    Vector3 dirF = r.normalize();

                    // Calculate the gravitational force vector
                    Vector3 F = dirF.multiplyBy(magF);

                    body.applyForce(F);
                
            }
        }
    }

    public static void updatePhysicsObjects()
    {
        for (PhysicsObject physicsObject : physicsObjectsToUpdate)
        {
            physicsObject.update();
        }

        System.out.println("test");
    }
}
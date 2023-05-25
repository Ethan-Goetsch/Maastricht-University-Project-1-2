package group9.project.States.Rocket;

import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.PhysicsSettings;
import group9.project.States.IStateManager;
import group9.project.Utility.Interfaces.ITargetable;
import group9.project.Utility.Math.Vector3;

public class DirectRocketState extends RocketState
{
    private ITargetable target;

    public DirectRocketState(IStateManager newStateManager, RocketShipObject newRocketShip, ITargetable newTarget)
    {
        super(newStateManager, newRocketShip);

        target = newTarget;
    }

    @Override
    public void onStateEnter()
    {
        
    }

    @Override
    public void onStateExit()
    {

    }
    
    /**
     * Updates the Rocket Ship's movement
     */
    @Override
    public void update()
    {
        tickMovement();
    }

    private void tickMovement()
    {
        Vector3[] state = rocketShip.getDifferentialSolver().solvePhysicsEquation(rocketShip.getPosition(), rocketShip.getVelocity(), rocketShip.getAcceleration(), PhysicsSettings.getStepSize(), rocketShip.getPhysicsObjectType());

        rocketShip.setPosition(state[0]);

        rocketShip.setVelocity(state[1]);
    }

    @Override
    public String getDescription()
    {
        return "Direct to " + target.getDescription() + " State";
    }
}
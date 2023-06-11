package group9.project.States.Rocket;

import group9.project.Data.Data;
import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.States.IState;
import group9.project.States.IStateManager;
import group9.project.States.Transitions.StateTransition;

public class RocketStateManager implements IStateManager
{
    private RocketShipObject rocketShip;

    private RocketState currentRocketState;

    public RocketStateManager(RocketShipObject newRocketShip)
    {
        rocketShip = newRocketShip;

        createRocketStates();
    }

    private void createRocketStates()
    {
        RocketState launchFromEarthState = new LaunchRocketState(this, rocketShip, LaunchToTitanOptimization.getInstance());

        RocketState directToTitanState = new DirectRocketState(this, rocketShip, PhysicsObjectData.getInstance().getTitanObject());

        RocketState orbitTitanState = new OrbitRocketState(this, rocketShip, PhysicsObjectData.getInstance().getTitanObject(), () -> PhysicsObjectData.getInstance().isRocketShipInTitanOrbit(), Data.getMonthsAsSeconds(0.1));

        RocketState landTitanState = new LandRocketState(this, rocketShip, PhysicsObjectData.getInstance().getTitanObject());

        RocketState launchFromTitanState = new LaunchRocketState(this, rocketShip, LaunchToEarthOptimization.getInstance());

        RocketState directoToEarthState = new DirectRocketState(this, rocketShip, PhysicsObjectData.getInstance().getEarthObject());


        launchFromEarthState.addStateTransition(new StateTransition(directToTitanState, () -> !PhysicsObjectData.getInstance().isRocketShipInEarthOrbit()));

        directToTitanState.addStateTransition(new StateTransition(orbitTitanState, () -> orbitTitanState.canEnterState()));

        orbitTitanState.addStateTransition(new StateTransition(landTitanState, () -> orbitTitanState.canExitState()));

        landTitanState.addStateTransition(new StateTransition(launchFromTitanState, () -> landTitanState.canExitState()));

        launchFromTitanState.addStateTransition(new StateTransition(directoToEarthState, () -> !PhysicsObjectData.getInstance().isRocketShipInTitanOrbit()));


        transitionToState(launchFromEarthState);
    }

    /**
     * Updates the Rocket Ship's current State and then checks if it can transition to a different state
     */
    @Override
    public void tickState()
    {
        currentRocketState.update();

        currentRocketState.checkStateTransitions();
    }

    /**
     * Transitions to a state. Raising the onStateExit and onStateEnter methods of the current state and next state
     * 
     * @param state the state to transition to
     */
    @Override
    public void transitionToState(IState state)
    {
        if (currentRocketState != null)
        {
            currentRocketState.onStateExit();
        }

        //System.out.println("Transitioned From" + " | " + (currentRocketState == null ? "Root State" : currentRocketState.getDescription()) + " | -> | " + state.getDescription() + " |");

        currentRocketState = (RocketState) state;

        currentRocketState.onStateEnter();
    }
}
package group9.project.States.Rocket;

import group9.project.Data.Data;
import group9.project.Optimization.LaunchToEarthOptimization;
import group9.project.Optimization.LaunchToTitanOptimization;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.Settings.SimulationSettings;
import group9.project.States.IState;
import group9.project.States.IStateManager;
import group9.project.States.Transitions.StateTransition;

public class RocketStateManager implements IStateManager
{
    private RocketShipObject rocketShipObject;

    private RocketState currentRocketState;

    public RocketStateManager(RocketShipObject newRocketShipObject)
    {
        rocketShipObject = newRocketShipObject;

        createRocketStates();
    }

    private void createRocketStates()
    {
        RocketState launchFromEarthState = new LaunchRocketState(this, rocketShipObject, LaunchToTitanOptimization.getInstance());

        RocketState directToTitanState = new DirectRocketState(this, rocketShipObject, PhysicsObjectData.getInstance().getTitanObject());

        RocketState orbitTitanState = new OrbitRocketState(this, rocketShipObject, PhysicsObjectData.getInstance().getTitanObject(), Data.getMonthsAsSeconds(1));

        RocketState launchFromTitanState = new LaunchRocketState(this, rocketShipObject, LaunchToEarthOptimization.getInstance());

        RocketState directoToEarthState = new DirectRocketState(this, rocketShipObject, PhysicsObjectData.getInstance().getEarthObject());


        launchFromEarthState.addStateTransition(new StateTransition(directToTitanState, () -> !PhysicsObjectData.getInstance().isRocketShipInEarthOrbit()));

        directToTitanState.addStateTransition(new StateTransition(orbitTitanState, () -> ((OrbitRocketState) orbitTitanState).canEnterOrbit()));

        orbitTitanState.addStateTransition(new StateTransition(launchFromTitanState, () -> ((OrbitRocketState) orbitTitanState).canExitOrbit()));

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

        System.out.println("Transitioned From" + " | " + (currentRocketState == null ? "Root State" : currentRocketState.getDescription()) + " | -> | " + state.getDescription() + " | @ " + SimulationSettings.getSimulationTime());

        currentRocketState = (RocketState) state;

        currentRocketState.onStateEnter();
    }
}
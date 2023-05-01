package group9.project.Physics.Objects;

import java.util.ArrayList;

import group9.project.Solvers.DifferentialSolver;
import group9.project.States.IState;
import group9.project.States.IStateManager;
import group9.project.States.Rocket.DirectFlightRocketState;
import group9.project.States.Rocket.RocketState;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.IDrawable;
import group9.project.Utility.Math.Vector3;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RocketShipObject extends PhysicsObject implements IDrawable, IStateManager
{
    private RocketState currentRocketState;


    private double thrusterForce;

    private double impulseForce;

    private double fuel;


    private Pane rocketShipPane;

    private Rectangle shape;

    private Label shapeLabel;

    public double getThrusterForce()
    {
        return thrusterForce;
    }

    public double getImpulseForce()
    {
        return impulseForce;
    }

    public double getFuel()
    {
        return fuel;
    }

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int shipWidth, int shipHeight, Color shipColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);


        DrawableManager.getInstance().add(this);

        createDrawableUI(shipWidth, shipHeight, shipColour);

        createRocketStates();
    }

    private void createDrawableUI(double shipWidth, double shipHeight, Color shipColour)
    {
        rocketShipPane = new Pane();


        shapeLabel = GUI.createLabel(physicsObjectType.toString());

        shapeLabel.setTextFill(Color.WHITE);

        shapeLabel.setTranslateX(-12.5);

        shapeLabel.setTranslateY(-20);


        shape = new Rectangle();

        shape.setFill(shipColour);

        shape.setWidth(shipWidth);
        
        shape.setHeight(shipHeight);


        rocketShipPane.getChildren().add(shapeLabel);

        rocketShipPane.getChildren().add(shape);
    }

    private void createRocketStates()
    {
        currentRocketState = new DirectFlightRocketState(this, new ArrayList<>());
    }

    @Override
    public void setForce(Vector3 newForce)
    {
        force = newForce;
    }

    public void setThrusterForce(double newThrusterForce)
    {
        thrusterForce = newThrusterForce;
    }

    public void updateFuel(double value)
    {
        fuel += value;
    }

    @Override
    public void update()
    {
        tickState();
    }

    @Override
    public Node getDrawable()
    {
        return rocketShipPane;
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(position);

        rocketShipPane.setTranslateX(scaledVector.getX());

        rocketShipPane.setTranslateY(scaledVector.getY());
    }

    @Override
    public void transitionToState(IState state)
    {
        if (currentRocketState != null)
        {
            currentRocketState.onStateExit();
        }

        currentRocketState = (RocketState) state;

        currentRocketState.onStateEnter();
    }

    @Override
    public void tickState()
    {
        currentRocketState.tick();

        currentRocketState.checkStateTransitions();
    }
}
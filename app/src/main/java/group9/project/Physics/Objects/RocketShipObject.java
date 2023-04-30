package group9.project.Physics.Objects;

import group9.project.Settings.PhysicsSettings;
import group9.project.Solvers.DifferentialSolver;
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

public class RocketShipObject extends PhysicsObject implements IDrawable
{
    private double thrusterForce;

    private double impulseForce;

    private double fuel;

    private Pane rocketShipPane;

    private Rectangle shape;

    private Label shapeLabel;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int shipWidth, int shipHeight, Color shipColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);

        DrawableManager.getInstance().add(this);

        createShipUI(shipWidth, shipHeight, shipColour);
    }

    private void createShipUI(double shipWidth, double shipHeight, Color shipColour)
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

    @Override
    public void setForce(Vector3 newForce)
    {
        force = newForce;
    }

    private void updateFuel(double value)
    {
        fuel += value;
    }

    public void setThrusterForce(double newThrusterForce)
    {
        thrusterForce = newThrusterForce;
    }

    @Override
    public void update()
    {
        impulseForce = thrusterForce * PhysicsSettings.getSimulationStepTime() - thrusterForce;

        setVelocity(velocity.add(new Vector3(impulseForce / mass, 0, 0)));


        Vector3[] state = differentialSolver.solveEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsSettings.getSimulationStepTime());

        setPosition(state[0]);

        setVelocity(state[1]);


        updateFuel(impulseForce);
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
}
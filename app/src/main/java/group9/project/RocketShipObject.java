package group9.project;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RocketShipObject extends PhysicsObject
{
    private double thrusterForce;

    private double impulseForce;

    private double fuel;


    private Pane rocketShipPane;

    private Rectangle shape;

    private Label shapeLabel;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int width, int height, Color shipColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);


        setThrusterForce(0);

        rocketShipPane = new Pane();


        shapeLabel = GUI.createLabel(newPhysicsObjectType.toString());

        shapeLabel.setTextFill(Color.WHITE);

        shapeLabel.setTranslateX(-12.5);

        shapeLabel.setTranslateY(-20);


        shape = new Rectangle();

        shape.setFill(shipColour);

        shape.setWidth(width);
        
        shape.setHeight(height);


        rocketShipPane.getChildren().add(shapeLabel);

        rocketShipPane.getChildren().add(shape);
    }

    @Override
    public void setForce(Vector3 newForce)
    {
        force = newForce.add(new Vector3(thrusterForce, 0, 0));
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
        impulseForce = thrusterForce * PhysicsEngine.getSimulationStepTime() - thrusterForce;

        setVelocity(velocity.add(new Vector3(impulseForce / mass, 0, 0)));


        Vector3[] state = differentialSolver.solveEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsEngine.getSimulationStepTime());

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
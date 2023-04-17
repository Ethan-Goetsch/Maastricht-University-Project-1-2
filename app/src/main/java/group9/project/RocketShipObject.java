package group9.project;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RocketShipObject extends PhysicsObject
{
    private Pane rocketShipPane;

    private Rectangle shape;

    private Label shapeLabel;

    public RocketShipObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, int width, int height, Color shipColour)
    {
        super(startingPosition, startingVelocity, mass, newDifferentialSolver, newPhysicsObjectType);


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
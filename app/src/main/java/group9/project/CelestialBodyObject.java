package group9.project;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CelestialBodyObject extends PhysicsObject
{
    private Pane celestialBodyPane;

    private Circle shape;

    private Label shapeLabel;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double mass, PhysicsObjectType newPhysicsObjectType, double planetRadius, double labelOffset,Color planetColour)
    {
        super(startingPosition, startingVelocity, mass, newPhysicsObjectType);

        celestialBodyPane = new Pane();


        shapeLabel = GUI.createLabel(newPhysicsObjectType.toString());

        shapeLabel.setTextFill(Color.WHITE);

        shapeLabel.setTranslateX(-12.5);

        shapeLabel.setTranslateY(-labelOffset);
        

        shape = new Circle();

        shape.setFill(planetColour);

        shape.setRadius(planetRadius);


        celestialBodyPane.getChildren().add(shapeLabel);

        celestialBodyPane.getChildren().add(shape);
    }

    @Override
    public void update()
    {
        setPosition(EulerSolver.getNewPosition(position, velocity));

        setVelocity(EulerSolver.getNewVelocity(velocity, acceleration));
    }

    @Override
    public Node getDrawable()
    {
        return celestialBodyPane;
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(position);


        celestialBodyPane.setTranslateX(scaledVector.getX());

        celestialBodyPane.setTranslateY(scaledVector.getY());
    }
}
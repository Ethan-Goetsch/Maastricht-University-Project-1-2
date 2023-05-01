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
import javafx.scene.shape.Circle;

public class CelestialBodyObject extends PhysicsObject implements IDrawable
{
    private Pane celestialBodyPane;

    private Circle shape;

    private Label shapeLabel;

    public CelestialBodyObject(Vector3 startingPosition, Vector3 startingVelocity, double newMass,  DifferentialSolver newDifferentialSolver, PhysicsObjectType newPhysicsObjectType, double planetRadius, double labelOffset, Color planetColour)
    {
        super(startingPosition, startingVelocity, newMass, newDifferentialSolver, newPhysicsObjectType);

        DrawableManager.getInstance().add((IDrawable)this);

        createDrawableUI(planetRadius, labelOffset, planetColour);
    }

    private void createDrawableUI(double planetRadius, double labelOffset, Color planetColour)
    {
        celestialBodyPane = new Pane();


        shapeLabel = GUI.createLabel(physicsObjectType.toString());

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
        Vector3[] state = differentialSolver.solveEquation(getPosition(), getVelocity(), getAcceleration(), PhysicsSettings.getStepTime());

        setPosition(state[0]);

        setVelocity(state[1]);
    }

    @Override
    public Node getDrawable()
    {
        return celestialBodyPane;
    }

    /**
     * Converts the Celestial Body's real world position to a screen position and moves this object's Circle to the screen position
    */
    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(position);

        celestialBodyPane.setTranslateX(scaledVector.getX());

        celestialBodyPane.setTranslateY(scaledVector.getY());
    }
}
package group9.project.UI.Drawable;

import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawableCelestialBodyUI extends DrawableUI
{
    private Circle drawableShape;

    private double planetRadius;

    private double labelOffset;

    private String labelText;

    private Color planetColour;

    public DrawableCelestialBodyUI(String name, CelestialBodyObject physicsObject, double newPlanetRadius, double newLabelOffset, String newLabelText, Color newPlanetColour)
    {
        super(name, physicsObject);


        planetRadius = newPlanetRadius;

        labelOffset = newLabelOffset;

        labelText = newLabelText;

        planetColour = newPlanetColour;


        drawablePosition = ScaleConverter.worldToScreenPosition(physicsObject.getPosition());

        createDrawableUI();
    }

    @Override
    public void createDrawableUI()
    {
        drawablePane = new Pane();


        drawableLabel = GUI.createLabel(labelText);

        drawableLabel.setTextFill(Color.WHITE);

        drawableLabel.setTranslateX(-12.5);

        drawableLabel.setTranslateY(-labelOffset);
        

        drawableShape = new Circle();

        drawableShape.setFill(planetColour);

        drawableShape.setRadius(planetRadius);


        drawablePane.getChildren().add(drawableLabel);

        drawablePane.getChildren().add(drawableShape);
    }
}
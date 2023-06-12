package group9.project.UI.Drawable;

import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawableRocketShipUI extends DrawableUI
{
   
    private Rectangle drawableShape;


    private double shipWidth;

    private double shipHeight;


    private double labelOffset;


    private String labelText;

    private Color shipColour;

    public DrawableRocketShipUI(double newShipWidth, double newShipHeight, double newLabelOffset, String newLabelText, Color newShipColour, Vector3 newDrawablePosition)
    {
        super();


        shipWidth = newShipWidth * ScaleConverter.getScaleSize();

        shipHeight = newShipHeight * ScaleConverter.getScaleSize();


        labelOffset = newLabelOffset;


        labelText = newLabelText;

        shipColour = newShipColour;


        drawablePosition = newDrawablePosition;


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


        drawableShape = new Rectangle();

        drawableShape.setFill(shipColour);

        drawableShape.setWidth(shipWidth);
        
        drawableShape.setHeight(shipHeight);


        drawablePane.getChildren().add(drawableLabel);

        drawablePane.getChildren().add(drawableShape);
    }

    @Override
    public void update(Vector3 newDrawablePosition)
    {
        super.update(newDrawablePosition);


        drawableShape.setScaleX(ScaleConverter.getScaleSize());

        drawableShape.setScaleY(ScaleConverter.getScaleSize());


        drawableLabel.setTranslateY(-labelOffset * ScaleConverter.getScaleSize());
    }
}
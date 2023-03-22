package group9.project;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GUI
{
    public static void setBackground(Pane pane, String colourName)
    {
        BackgroundFill backgroundFill = new BackgroundFill(Color.valueOf(colourName), new CornerRadii(5), new Insets(0.1));

        Background background = new Background(backgroundFill);

        pane.setBackground(background);
    }

    public static HBox createHBox(double width, double height, double spacing, Insets padding)
    {
        HBox newHBox = new HBox();

        newHBox.setSpacing(spacing);

        newHBox.setPadding(padding);

        return newHBox;
    }

    public static Label createLabel(String labelText)
    {
        Label newLabel = new Label(labelText);

        return newLabel;
    }

    public static Slider createSlider(double minValue, double maxValue, double startingValue, ChangeListener<Number> valueChangeListener)
    {
        Slider newSlider = new Slider(minValue, maxValue, startingValue);

        newSlider.setBlockIncrement(0.1);

        newSlider.setShowTickLabels(true);

        newSlider.setShowTickMarks(true);

        newSlider.valueProperty().addListener(valueChangeListener);

        return newSlider;
    }
}
package group9.project;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class OrbitTrail{
   
    //ArrayList<Line> lines;
    LinkedList<Line> lines;

    private Vector3 prevPosition = null;

    public OrbitTrail()
    {
        //lines = new ArrayList<Line>();
        lines = new LinkedList<>();
    }

    public void addPosition(Vector3 position)
    {
        position = ScaleConverter.worldToScreenPosition(position);
        if (prevPosition != null)
        {
            Line line = new Line(prevPosition.getX(), prevPosition.getY(), position.getX(), position.getY());
            line.setStrokeWidth(1);
            line.setStroke(Color.WHITE);
            line.setOpacity(0.3);
            lines.add(line);
        } 
        prevPosition = position;

        if (lines.size() > 5)
        {
            lines.removeFirst();
        }
    }

    public LinkedList<Line> getLines()
    {
        return lines;
    }

}

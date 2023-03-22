package group9.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Label;

public class DateView{
    
    DateCalculator dateCalculator;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date startingDate;
    Label dateLabel;

    public DateView()
    {
        dateLabel = new Label();
        try {
            startingDate = dateFormat.parse("01-04-2023");
            dateCalculator = new DateCalculator(startingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        Date date = dateCalculator.getDate((long)PhysicsEngine.getSimulationTime());
        dateLabel.setText("Current date: " + dateFormat.format(date));
    }

    public Label getLabel()
    {
        return dateLabel;
    }
}

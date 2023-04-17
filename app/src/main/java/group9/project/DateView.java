package group9.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Label;

public class DateView implements IUpdateable
{
    private DateCalculator dateCalculator;

    private DateFormat dateFormat;


    private Date startingDate;

    private Date currentDate;


    private Label dateLabel;

    public Label getLabel()
    {
        return dateLabel;
    }

    public DateView()
    {
        dateFormat= new SimpleDateFormat("dd-MM-yyyy");

        dateLabel = GUI.createLabel("");

        try
        {
            startingDate = dateFormat.parse("01-04-2023");

            dateCalculator = new DateCalculator(startingDate);

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update()
    {
        currentDate = dateCalculator.getDate((long)SimulationSettings.getSimulationTime());

        dateLabel.setText("Current date: " + dateFormat.format(currentDate));
    }
}
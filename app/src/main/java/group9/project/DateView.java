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

    public static Date startingDate;
    public static Date startDate;

    public static DateView instance = null;

    private Label dateLabel;

    public static Date currentDate;

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
            currentDate = startingDate;
            startDate = startingDate;

            dateCalculator = new DateCalculator(startingDate);

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        instance = this;
    }

    public static DateView getInstance()
    {
        if (instance == null)
        {
            instance = new DateView();
        }
        return instance;
    }

    public void reset()
    {
        try
        {
            startingDate = dateFormat.parse("01-04-2023");
            currentDate = startingDate;
            startDate = startingDate;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update()
    {
        Date date = dateCalculator.getDate((long)SimulationSettings.getSimulationTime());
        currentDate = date;

        dateLabel.setText("Current date: " + dateFormat.format(date));
    }

}
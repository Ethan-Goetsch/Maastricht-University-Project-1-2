package group9.project.Utility.Date;

import java.util.Calendar;
import java.util.Date;

import group9.project.Settings.SimulationSettings;

public class DateCalculator
{
    private Date startDate;
    private static DateCalculator instance;
    
    public static DateCalculator getInstance()
    {
        if (instance == null)
        {
            instance = new DateCalculator(new Date(122, 04, 01));
        }
        return instance;
    }

    private DateCalculator(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getDate(long seconds)
    {
        Calendar calender = Calendar.getInstance();

        calender.setTime(startDate);

        calender.add(Calendar.SECOND, (int)seconds);

        return calender.getTime();
    }
    
    public Date getCurrentDate()
    {
        Calendar calender = Calendar.getInstance();

        calender.setTime(startDate);

        calender.add(Calendar.SECOND, (int)SimulationSettings.getSimulationTime());

        return calender.getTime();
    } 
}
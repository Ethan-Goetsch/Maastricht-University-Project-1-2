package group9.project;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator
{
    private Date startDate;

    public DateCalculator(Date startDate)
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
}
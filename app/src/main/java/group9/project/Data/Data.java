package group9.project.Data;

public class Data
{
    private final static double MIN_ORBIT_DISTANCE = 100;

    private final static double MAX_ORBIT_DISTANCE = 5000;


    private static final double SECONDS_IN_MONTH = 2.628E+6;

    /**
     * @return the minimum orbit distance to be in a planet's orbit. Defined in the Manual
     */
    public static double getMinOrbitDistance()
    {
        return MIN_ORBIT_DISTANCE;
    }

    /**
     * @return the maximum orbit distance to be in a planet's orbit. Defined in the Manual
     */
    public static double getMaxOrbitDistance()
    {
        return MAX_ORBIT_DISTANCE;
    }


    /**
     * @param distance the distance between the object and a planet
     * 
     * @return true if the distance is less than or equal to the maximum orbit distance and greater than or equal to the minimum orbit distance
     */
    public static boolean inOrbit(double distance)
    {
        return distance <= getMaxOrbitDistance() && distance >= getMinOrbitDistance();
    }

    /**
     * @return the amount of seconds in a month. Definied in my book. I calculated this manually, counting each second, feeling each second
     */
    public static double getSecondsInMonth()
    {
        return SECONDS_IN_MONTH;
    }

    
    /**
     * @param months the amount of months to calculate the seconds for
     * 
     * @return the number of seconds in the specified amount of months
     */
    public static double getMonthsAsSeconds(double months)
    {
        return months * getSecondsInMonth();
    }
}
package group9.project.Data;

public class Data
{
    private final static double EARTH_RADIUS = 6371;

    private final static double TITAN_RADIUS = 2574.7;


    private final static double MIN_ORBIT_DISTANCE = 100;

    private final static double MAX_ORBIT_DISTANCE = 300;


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
     * @param distance the distance between the object and Titan
     * 
     * @return true if the distance is less than or equal to the maximum orbit distance and greater than or equal to the minimum orbit distance
     */
    public static boolean inTitanOrbit(double distance)
    {
        return distance <= TITAN_RADIUS + getMaxOrbitDistance() && distance >= TITAN_RADIUS + getMinOrbitDistance();
    }

    /**
     * @param distance the distance between the object and Earth
     * 
     * @return true if the distance is less than or equal to the maximum orbit distance and greater than or equal to the minimum orbit distance
     */
    public static boolean inEarthOrbit(double distance)
    {
        return distance <= EARTH_RADIUS + getMaxOrbitDistance() && distance >= EARTH_RADIUS + getMinOrbitDistance();
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
package group9.project;

public class Converter
{
    private static final double SOLAR_SYSTEM_RADIUS = 4.5e9;

    private static final double SCALE_RADIUS = 500;
    private static double WIDTH = (double) PhysicsVisualizer.WIDTH;
    private static double HEIGHT = (double) PhysicsVisualizer.HEIGHT;

    public static Vector3 scaleToScreen(Vector3 v)
    {
        double x = (v.getX()/SOLAR_SYSTEM_RADIUS) * (WIDTH/2) + (WIDTH/2);
        /* 
        double x = v.getX() / SOLAR_SYSTEM_RADIUS;
        double y = v.getY() / SOLAR_SYSTEM_RADIUS;
        double z = v.getZ() / SOLAR_SYSTEM_RADIUS;
        */

        double y = (v.getY()/SOLAR_SYSTEM_RADIUS) * (HEIGHT/2) + (HEIGHT/2);

        double z = (v.getZ()/SOLAR_SYSTEM_RADIUS) * (WIDTH/2) + (WIDTH/2);

        //System.out.println("(1) --> x: " + x + ", y: " + y + ", z: " + z);

        return new Vector3(x, y, z);
    }

    public static Vector3 scaleToSolarSystem(Vector3 v)
    {
        double x = ((v.getX() - WIDTH/2) / (WIDTH/2)) * SOLAR_SYSTEM_RADIUS;
        /* 
        double x = v.getX() * SOLAR_SYSTEM_RADIUS;
        double y = v.getY() * SOLAR_SYSTEM_RADIUS;
        double z = v.getZ() * SOLAR_SYSTEM_RADIUS;
        */

        double y = ((v.getY() - HEIGHT/2) / (HEIGHT/2)) * SOLAR_SYSTEM_RADIUS;

        double z = ((v.getZ() - WIDTH/2) / (WIDTH/2)) * WIDTH;

        //System.out.println("(2) --> x: " + x + ", y: " + y + ", z: " + z);

        return new Vector3(x, y, z);
    }

    public static double scaleToScreen(double num) {
        return (num/SOLAR_SYSTEM_RADIUS) * (SCALE_RADIUS/2) + (SCALE_RADIUS/2);
    }

    public static double scaleToSolarSystem(double num) {
        return ((num - SCALE_RADIUS/2) / (SCALE_RADIUS/2)) * SOLAR_SYSTEM_RADIUS;
    }
}
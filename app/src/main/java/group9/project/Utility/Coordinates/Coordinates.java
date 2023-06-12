package group9.project.Utility.Coordinates;

import group9.project.Utility.Math.Vector3;

public class Coordinates
{
    public static Vector3 RelativeTo(Vector3 coordinates, Vector3 relativeToCoordinates)
    {
        return coordinates.subtract(relativeToCoordinates);
    }   
}
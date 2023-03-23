package group9.project;


import java.util.Date;

import javafx.scene.paint.Color;

public class RocketShipGA extends RocketShipObject{

    private double closestDistance = 0;
    private double xAtClosestDistance;
    private double yAtClosestDistance;
    private double zAtClosestDistance;
    private Date timeAtClosestPosition = null;
    private int width, height;
    private Color colour;
    
    public RocketShipGA(Vector3 startingPosition, Vector3 startingVelocity, double mass, PhysicsObjectType name, int width, int height, Color shipColour)
    {
        super(startingPosition, startingVelocity, mass, name, width, height, shipColour);
        this.colour = shipColour;
        this.height = height;
        this.width = width;
    }

    public void logPositionAtTime(Vector3 position, Date time)
    {
        if (closestDistance == 0)
        {
           closestDistance = Math.abs(Mathematics.getDistance(PhysicsObjectData.getInstance().titanObject.getPosition(), position));
           timeAtClosestPosition = time;
           return; 
        }
        double distance = Math.abs(Mathematics.getDistance(PhysicsObjectData.getInstance().titanObject.getPosition(), position));
        if (distance < closestDistance)
        {
            closestDistance = distance;
            timeAtClosestPosition = time;
            xAtClosestDistance = Math.abs(PhysicsObjectData.getInstance().titanObject.getPosition().getX() - position.getX());
            yAtClosestDistance = Math.abs(PhysicsObjectData.getInstance().titanObject.getPosition().getY() - position.getY());
            zAtClosestDistance = Math.abs(PhysicsObjectData.getInstance().titanObject.getPosition().getZ() - position.getZ());
        }
    }

    public double getClosestX()
    {
        return xAtClosestDistance;
    }

    public double getClosestY()
    {
        return yAtClosestDistance;
    }

    public double getClosestZ()
    {
        return zAtClosestDistance;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Color getColour()
    {
        return colour;
    }

    public Vector3 getStartingPosition()
    {
        return startingPosition;
    }

    public double getClosestDistance()
    {
        return closestDistance;
    }

    public Date getTimeAtClosestPosition()
    {
        return timeAtClosestPosition;
    }

    public Vector3 getStartingVelocity()
    {
        return startingVelocity;
    }

    public void setStartingVelocity(Vector3 v)
    {
        startingVelocity = v;
    }

}

package group9.project.Wind;

import group9.project.Utility.Math.Vector2;

public abstract class WindModel
{
    protected double intensity;

    protected double frequency;

    public WindModel(double intensity, double frequency)
    {
        this.intensity = intensity / 1000;

        this.frequency = frequency;
    }

    public abstract Vector2 generateRandomWind(double distanceToSurface);
}
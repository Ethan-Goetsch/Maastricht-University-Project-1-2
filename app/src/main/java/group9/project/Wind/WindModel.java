package group9.project.Wind;

public abstract class WindModel
{
    protected double intensity;

    protected double frequency;

    public WindModel(double intensity, double frequency)
    {
        this.intensity = intensity;

        this.frequency = frequency;
    }

    public abstract double generateRandomWind(double distanceToSurface);
}
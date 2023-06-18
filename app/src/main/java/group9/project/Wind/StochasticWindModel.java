package group9.project.Wind;

import group9.project.Utility.RandomGenerator;

public class StochasticWindModel extends WindModel
{
    public StochasticWindModel(double intensity, double frequency)
    {
        super(intensity, frequency);
    }

    @Override
    public double generateRandomWind(double distanceToSurface)
    {
        double randomFrequencyRoll = RandomGenerator.generateRandom(0, 1);

        if (distanceToSurface == 0 || randomFrequencyRoll > frequency)
        {
            return 0;
        }

        return (RandomGenerator.generateRandom(-intensity, intensity) + Math.atan(distanceToSurface)) / distanceToSurface;
    }
}
package group9.project.Wind;

import group9.project.Utility.RandomGenerator;
import group9.project.Utility.Math.Vector2;

public class StochasticWindModel extends WindModel
{
    public StochasticWindModel(double intensity, double frequency)
    {
        super(intensity, frequency);
    }

    @Override
    public Vector2 generateRandomWind(double distanceToSurface)
    {
        double randomFrequencyRoll = RandomGenerator.generateRandom(0, 1);

        if (distanceToSurface == 0 || randomFrequencyRoll > frequency)
        {
            return new Vector2();
        }

        double randomWind = RandomGenerator.generateRandom(-intensity, intensity) * (Math.atan(distanceToSurface) / distanceToSurface);

        double randomWindX = 0;

        double randomWindY = 0;

        double randomDirectionRoll = RandomGenerator.generateRandom(0, 1);

        if (randomDirectionRoll < 0.5)
        {
            randomWindX = randomWind;
        }
        else
        {
            randomWindY = randomWind;
        }

        return new Vector2(randomWindX, randomWindY);
    }
}
package group9.project.Utility;

import java.util.Random;

public class RandomGenerator
{
    private static Random random = new Random();

    public static double generateRandom(double min, double max)
    {
        return random.nextDouble() * (max - min) + min;
    }
}
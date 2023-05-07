package group9.project.Physics.Objects;

public enum PhysicsObjectType
{
    Sun(0), Mercury(1), Venus(2), Earth(3), Moon(4), Mars(5), Jupiter(6), Saturn(7), Titan(8), Neptune(9), Uranus(10), Rocket(11);

    private final int value;

    private PhysicsObjectType(final int newValue)
    {
        value = newValue;
    }

    public int getValue()
    {
        return value;
    }
}
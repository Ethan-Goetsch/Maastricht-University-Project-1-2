package group9.project;

public class PhysicsObjectData implements IStartable
{
    //#region Singleton
    private PhysicsObjectData instance;

    public PhysicsObjectData getInstance()
    {
        if (instance == null)
        {
            instance = new PhysicsObjectData();
        }

        return instance;
    }
    //#endregion

    private CelestialBodyObject titanObject;

    private RocketShipObject rocketShipObject;

    public PhysicsObjectData()
    {
        instance = this;
    }

    @Override
    public void start()
    {

    } 
}
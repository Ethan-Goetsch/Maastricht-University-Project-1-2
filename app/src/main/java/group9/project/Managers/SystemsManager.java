package group9.project.Managers;

import group9.project.Physics.Managers.PhysicsEngine;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Interfaces.IResetable;
import group9.project.Utility.Interfaces.IStartable;

public class SystemsManager implements IStartable, IResetable
{
    //#region Singleton
    private static SystemsManager instance;

    public static SystemsManager getInstance()
    {
        if (instance == null)
        {
            instance = new SystemsManager();
        }

        return instance;
    }
    //#endregion

    private SystemsManager()
    {

    }

    /**
     * Starts the System Manager
     */
    @Override
    public void start()
    {
        createSystems();
    }

    private void createSystems()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();

        ScaleConverter.getInstance().start();

        EventManager.getInstance().start();
    }

    /**
     * Resets the System Manager
     */
    @Override
    public void reset()
    {
        PhysicsObjectData.getInstance().start();

        PhysicsEngine.getInstance().start();
    }
}
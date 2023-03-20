package group9.project;

public class SystemOrbitView extends PaneView
{
    public SystemOrbitView(int parentWidth, int parentHeight, int widthPercentage, int heightPercentage)
    {
        super(parentWidth, parentHeight, widthPercentage, heightPercentage);

        start();
    }

    @Override
    public void start()
    {
        
    }

    @Override
    public void update()
    {
        getChildren().clear();

        for (PhysicsObject physicsObject : PhysicsEngine.getInstance().getPhysicsObjectsToUpdate())
        {
            getChildren().add(physicsObject.get)    
        }
    }
}
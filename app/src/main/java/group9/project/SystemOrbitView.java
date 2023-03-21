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
        update(1);
    }

    @Override
    public void update(double timeDelta)
    {
        getChildren().clear();

        for (IDrawable drawable : PhysicsEngine.getInstance().getPhysicsObjectsToUpdate())
        {
            getChildren().add(drawable.getShape());    
        }
    }
}
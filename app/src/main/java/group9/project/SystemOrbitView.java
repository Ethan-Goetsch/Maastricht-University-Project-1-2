package group9.project;

import javafx.scene.shape.Shape;

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
        update();
    }

    @Override
    public void update()
    {
        getChildren().clear();

        for (IDrawable drawable : PhysicsEngine.getInstance().getPhysicsObjectsToUpdate())
        {
            drawable.setShapePosition();

            Shape shape = drawable.getShape();

            getChildren().add(shape);    
        }
    }
}
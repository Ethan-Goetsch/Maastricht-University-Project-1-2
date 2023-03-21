package group9.project;

import javafx.scene.shape.Shape;

public class SystemOrbitView extends PaneView
{
    public SystemOrbitView(int width, int height)
    {
        super(width, height);

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
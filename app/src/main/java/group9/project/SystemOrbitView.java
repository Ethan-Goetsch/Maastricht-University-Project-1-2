package group9.project;

import javafx.scene.shape.Shape;

public class SystemOrbitView extends PaneView
{
    public SystemOrbitView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    @Override
    public void start()
    {
        setBackground();

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
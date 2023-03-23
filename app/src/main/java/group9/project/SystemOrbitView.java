package group9.project;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class SystemOrbitView extends PaneView
{

    public SystemOrbitView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    protected void start()
    {
        GUI.setBackground(this, "black");

        setMouseEvents();

        update();
    }

    private void setMouseEvents()
    {
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

            if (drawable instanceof PhysicsObject)
            {
                PhysicsObject physicsObject = (PhysicsObject)drawable;
                for (Line line : physicsObject.getOrbitTrail().getLines()) {
                    getChildren().add(line);
                }
            }
        }

        /* 
        for (Line line : PhysicsVisualizer.orbitTrail.getLines()) {
            getChildren().add(line);
        }
        */
    }
}
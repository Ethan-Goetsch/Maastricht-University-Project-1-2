package group9.project;

import javafx.scene.Node;
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
            drawable.draw();

            Node shape = drawable.getDrawable();

            getChildren().add(shape);

            // if (drawable instanceof PhysicsObject)
            // {
            //     PhysicsObject physicsObject = (PhysicsObject) drawable;

            //     for (Line line : physicsObject.getOrbitTrail().getLines())
            //     {
            //         getChildren().add(line);
            //     }
            // }
        }
    }
}
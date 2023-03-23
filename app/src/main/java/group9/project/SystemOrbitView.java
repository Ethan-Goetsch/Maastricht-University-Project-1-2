package group9.project;

import javafx.scene.Node;

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

        setPickOnBounds(true);

        update();
    }

    @Override
    public void update()
    {
        getChildren().clear();

        for (IDrawable drawable : PhysicsEngine.getInstance().getPhysicsObjectsToUpdate())
        {
            drawable.draw();

            Node shape = drawable.getDrawable();

            if (inView(shape))
            {
                getChildren().add(shape);
            }

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

    private boolean inView(Node node)
    {
        return node.getTranslateX() > 0 && node.getTranslateX() <= width && node.getTranslateY() > 0 && node.getTranslateY() <= height;
    }
}
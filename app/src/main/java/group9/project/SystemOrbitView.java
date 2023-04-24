package group9.project;

import javafx.scene.Node;
import java.util.Iterator;

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

        // iterate through drawables
        Iterator<IDrawable> drawableIterator = DrawableManager.getInstance().getIterator(); 

        while (drawableIterator.hasNext())
        {
            IDrawable drawable = drawableIterator.next();

            drawable.draw();

            Node shape = drawable.getDrawable();

            if (inView(shape))
            {
                getChildren().add(shape);
            }
        }
    }

    private boolean inView(Node node)
    {
        return node.getTranslateX() > 0 && node.getTranslateX() <= width && node.getTranslateY() > 0 && node.getTranslateY() <= height;
    }
}

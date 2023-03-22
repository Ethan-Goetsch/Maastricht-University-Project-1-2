package group9.project;

import javafx.scene.shape.Shape;

public class SystemOrbitView extends PaneView
{
    private double mouseClickedXPosition;

    private double mouseClickedYPosition;

    public SystemOrbitView(int newWidth, int newHeight)
    {
        super(newWidth, newHeight);

        start();
    }

    @Override
    public void start()
    {
        GUI.setBackground(this, "black");

        setMouseEvents();

        update();
    }

    private void setMouseEvents()
    {
        setOnMousePressed(event ->
        {
            mouseClickedXPosition = event.getX();

            mouseClickedYPosition = event.getY();
        });

        setOnMouseDragged(event->
        {
            if (event.isPrimaryButtonDown())
            {
                PhysicsVisualizer.addXDragOffset(event.getX() - Math.abs(mouseClickedXPosition));

                PhysicsVisualizer.addYDragOffset(event.getY() - Math.abs(mouseClickedYPosition));
            }
        });
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
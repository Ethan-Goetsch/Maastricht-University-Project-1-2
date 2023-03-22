package group9.project;

import javafx.scene.layout.Background;
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

        setOnMouseDragged(event ->
        {
            //PhysicsVisualizer.setXDragOffset(event.getX() - mouseClickedXPosition);

            //PhysicsVisualizer.setYDragOffset(event.getY() - mouseClickedYPosition);
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
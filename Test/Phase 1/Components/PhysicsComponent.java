package Components;

import java.awt.Graphics;
import javax.swing.JComponent;
import Interfaces.PhysicsItem;
import Managers.PhysicsVisualizer;
import Objects.PhysicsObject;

public abstract class PhysicsComponent extends JComponent implements PhysicsItem
{
    protected PhysicsObject physicsObject;

    public PhysicsComponent(PhysicsObject newPhysicsObject)
    {
        PhysicsVisualizer.getInstance().addPhysicsItem(this);

        physicsObject = newPhysicsObject;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void fixedUpdate()
    {
        repaint();
    }

    @Override
    public abstract void paintComponent(Graphics graphics);
}
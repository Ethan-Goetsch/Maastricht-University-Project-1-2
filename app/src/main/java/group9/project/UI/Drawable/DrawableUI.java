package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.PhysicsObject;

public abstract class DrawableUI implements IDrawable
{
    protected Spatial spatial;

    protected String name;

    protected PhysicsObject physicsObject;

    /**
     * Constructor.
     * @param name the name of the drawable
     * @param spatial the spatial to add to the scene graph
     */ 
    public DrawableUI(String name, Spatial spatial, PhysicsObject physicsObject)
    {
        this.name = name;

        this.spatial = spatial;
        
        this.physicsObject = physicsObject;
    }

    @Override
    public Spatial getDrawable()
    {
        return spatial;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public abstract float getPreferredViewDistance();

    @Override
    public abstract void draw();
    
    @Override
    public abstract DrawableUI clone();
}
package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;
import group9.project.Utility.Math.Vector3;

public abstract class DrawableUI implements IDrawable
{
    protected Vector3 drawablePosition;

    protected Spatial spatial;

    protected String name;

    public DrawableUI(String name, Spatial spatial)
    {
        this.name = name;
        this.spatial = spatial;
        //DrawableManager.getInstance().add(this);
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
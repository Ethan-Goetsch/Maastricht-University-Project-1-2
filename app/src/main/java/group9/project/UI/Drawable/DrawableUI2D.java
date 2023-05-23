package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;

public class DrawableUI2D implements IDrawable {
    
    private DrawableUI drawable;
    Spatial spatial;
    
    public DrawableUI2D(DrawableUI drawable)
    {
        this.drawable = drawable;
    }
    
    @Override
    public float getPreferredViewDistance()
    {
        return drawable.getPreferredViewDistance();
    }
    
    @Override
    public String getName()
    {
        return drawable.getName();
    }
    
    @Override
    public Spatial getDrawable()
    {
        return drawable.getDrawable().clone().scale(1/drawable.getDrawable().getLocalScale().length()).scale(100);
    }
    
    @Override
    public void draw()
    {
        drawable.draw();
    }

    @Override
    public DrawableUI2D clone()
    {
        return new DrawableUI2D(drawable.clone());
    }
    
}

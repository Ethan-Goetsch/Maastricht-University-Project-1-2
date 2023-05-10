package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;

public interface IDrawable
{
    public Spatial getDrawable();

    public void draw();

    public String getName();

    public float getPreferredViewDistance();
}
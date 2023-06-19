package group9.project.UI.Drawable;

import com.jme3.scene.Spatial;

public interface IDrawable
{
    /**
     * Returns the drawable's spatial.
     * @return the drawable's spatial
     */
    public Spatial getDrawable();

    /**
     * Updates the state of the drawable.
     */
    public void draw();

    /**
     * Returns the name of the drawable.
     * @return the drawable's name
     */
    public String getName();

    /**
     * Returns the default distance at which the camera should view the drawable from.
     * @return the drawable's preferred viewing distance.
     */
    public float getPreferredViewDistance();
}
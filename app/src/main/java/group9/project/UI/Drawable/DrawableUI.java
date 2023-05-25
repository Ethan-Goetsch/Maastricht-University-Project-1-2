package group9.project.UI.Drawable;

<<<<<<< HEAD
import group9.project.Physics.Objects.PhysicsObject;
import group9.project.UI.ScaleConverter;
=======
import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.PhysicsObject;
>>>>>>> jmonkeyengine
import group9.project.Utility.Math.Vector3;

public abstract class DrawableUI implements IDrawable
{
    protected Vector3 drawablePosition;

    protected Spatial spatial;

    protected String name;
    
    protected PhysicsObject physicsObject;

<<<<<<< HEAD
    protected String name;

    protected PhysicsObject physicsObject;

    public DrawableUI(String name, PhysicsObject physicsObject)
    {
        DrawableManager.getInstance().add(this);

        drawablePane = new Pane();

        this.name = name;

=======
    /**
     * Constructor.
     * @param name the name of the drawable
     * @param spatial the spatial to add to the scene graph
     */ 
    public DrawableUI(String name, Spatial spatial, PhysicsObject physicsObject)
    {
        this.name = name;
        this.spatial = spatial;
>>>>>>> jmonkeyengine
        this.physicsObject = physicsObject;
    }

    @Override
    public Spatial getDrawable()
    {
<<<<<<< HEAD
        return drawablePane;
    }

    public void update()
    {
        drawablePosition = ScaleConverter.worldToScreenPosition(physicsObject.getPosition());

        draw();
=======
        return spatial;
>>>>>>> jmonkeyengine
    }

    @Override
    public String getName()
<<<<<<< HEAD
    {
        return name;
    }

    @Override
    public void draw()
=======
>>>>>>> jmonkeyengine
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
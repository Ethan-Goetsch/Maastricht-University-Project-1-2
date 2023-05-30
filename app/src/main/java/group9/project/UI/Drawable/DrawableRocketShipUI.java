package group9.project.UI.Drawable;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class DrawableRocketShipUI extends DrawableUI
{
   
    private float scale;

    //private RocketShipObject physicsObject;

    /**
     * Constructor.
     * @param name the name of the drawable
     * @param scale the scale of the drawable
     * @param spatial the spatial to attach to the scene graph
     * @param rocketShipObject the rocket ship object which this drawable mirrors (movement of the rocket ship will be reflected by movement of the spatial))
     */
    public DrawableRocketShipUI(String name, float scale, Spatial spatial, RocketShipObject rocketShipObject)
    {
        super(name, spatial, rocketShipObject);

        this.scale = scale;

        this.name = name;
        
        this.spatial.setLocalScale(scale);

        //this.physicsObject = physicsObject;
    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(physicsObject.getPosition());
        
        spatial.setLocalTranslation((float)scaledVector.getX(), (float)scaledVector.getY(), (float)scaledVector.getZ()); 

        // set rotation of spatial to face in direction of motion:
        Vector3 dir = physicsObject.getDirection();
        Quaternion rotation = new Quaternion();
        rotation.lookAt(new Vector3f((float)dir.getX(), (float)dir.getY(), (float)dir.getZ()), new Vector3f(0f,1f,0f));
        Quaternion rotateY = new Quaternion();
        rotateY.fromAngleAxis(FastMath.PI/2, new Vector3f(0,1,0));
        rotation.mult(rotateY);
        spatial.setLocalRotation(rotation);
    }

    @Override
    public float getPreferredViewDistance()
    {
        return 24*scale;
    }
    
    @Override
    public DrawableRocketShipUI clone()
    {
        return new DrawableRocketShipUI(name, scale, spatial.clone(), (RocketShipObject)physicsObject);
    }

}
package group9.project.UI.Drawable;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import group9.project.Physics.Objects.RocketShipObject;
import group9.project.UI.GUI;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Math.Vector3;

public class DrawableRocketShipUI extends DrawableUI
{
   
    private float scale;

    private Spatial spatial;

    private RocketShipObject rocketShip;

    public DrawableRocketShipUI(String name, float scale, Vector3 newDrawablePosition, Spatial spatial, RocketShipObject rocketShip)
    {
        super();

        this.scale = scale;

        this.name = name;

        spatial.setLocalScale(scale);
        this.spatial = spatial;

        this.rocketShip = rocketShip;

        drawablePosition = newDrawablePosition;

    }

    @Override
    public void draw()
    {
        Vector3 scaledVector = ScaleConverter.worldToScreenPosition(rocketShip.getPosition());
        
        spatial.setLocalTranslation((float)scaledVector.getX(), (float)scaledVector.getY(), (float)scaledVector.getZ()); 

        // set rotation of spatial to face in direction of motion:
        Vector3 dir = rocketShip.getDirection();
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
        return 8*scale;
    }

}
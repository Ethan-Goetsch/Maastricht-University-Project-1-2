package group9.project.Events.Listeners;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import group9.project.Events.IEventListener;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.Camera.CinematicCameraControl;
import group9.project.UI.Camera.ICameraControlState;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.ScaleConverter;
import group9.project.Utility.Coordinates.Coordinates;
import group9.project.Utility.Math.Mathematics;
import group9.project.Utility.Math.Vector3;

public class CinematicCameraEnterDirectState implements IEventListener, ICameraControlState {
    
    private final double TARGET_DISTANCE_TO_ROCKET = 5;
    
    private boolean initialized = false;
    
    public CinematicCameraEnterDirectState()
    {
        
    }
    
    @Override
    public void onEvent()
    {
        CinematicCameraControl.setState(this);        
    }
    
    @Override
    public boolean isInitialised()
    {
        return initialized;
    }
    
    @Override
    public void init(Spatial spatial)
    {
        initialized = true;
    }
    
    @Override
    public void moveSpatial(Spatial spatial)
    {
        
        Vector3f rocketPosition = DrawableManager.getInstance().getObjectWithName("rocket").getDrawable().getLocalTranslation();
                
        double rocketSpeed = PhysicsObjectData.getInstance().getRocketShipSpeed();
        
        spatial.lookAt(rocketPosition, new Vector3f(0,1,0));
        
        Vector3f vectorToRocket = rocketPosition.subtract(spatial.getLocalTranslation());
        
        float spatialSpeed = (float)ScaleConverter.worldToScreenLength(rocketSpeed);
        if (vectorToRocket.length() > TARGET_DISTANCE_TO_ROCKET)
        {
            spatialSpeed *= 300;
        }
        spatial.move(vectorToRocket.normalize().mult(spatialSpeed));     
        
    }
}

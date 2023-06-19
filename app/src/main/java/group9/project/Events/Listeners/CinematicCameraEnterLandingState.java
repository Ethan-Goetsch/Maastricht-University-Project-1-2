
package group9.project.Events.Listeners;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import group9.project.Events.IEventListener;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.Camera.CinematicCameraControl;
import group9.project.UI.Camera.ICameraControlState;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.Utility.Math.Vector3;

public class CinematicCameraEnterLandingState implements IEventListener, ICameraControlState {
    
    private boolean initialised = false;
    
    public CinematicCameraEnterLandingState()
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
        return initialised;
    }
    
    @Override
    public void init(Spatial spatial)
    {
        DrawableUI titan = DrawableManager.getInstance().getObjectWithName("titan");
        Spatial titanSpatial = titan.getDrawable();
        Vector3f titanPosition = titanSpatial.getLocalTranslation();
        Vector3f targetSpatialPosition = titanPosition.add(0,0,100);
        spatial.setLocalTranslation(targetSpatialPosition);
        spatial.lookAt(titanPosition, new Vector3f(0,1,0));
        initialised = true;
    }
    
    @Override 
    public void moveSpatial(Spatial spatial)
    {
        DrawableUI titan = DrawableManager.getInstance().getObjectWithName("titan");
        Spatial titanSpatial = titan.getDrawable();
        Vector3f titanPosition = titanSpatial.getLocalTranslation();
        
        float targetDistance = 5;
        
        float distanceToTitan = titanPosition.subtract(spatial.getLocalTranslation()).length();
        if (distanceToTitan > targetDistance)
        {
            float speed = 0.1f;
            spatial.move(titanPosition.subtract(spatial.getLocalTranslation()).normalize().mult(speed));
        }
        
        
    }
    

}

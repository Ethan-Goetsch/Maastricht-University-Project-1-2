package group9.project.UI.Camera;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.Physics.Objects.CelestialBodyObject;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableUI;


public class CinematicCameraInitialState implements ICameraControlState {
    
    private boolean initialised = false;
    
    public CinematicCameraInitialState()
    {
        
    }
    
    @Override
    public boolean isInitialised()
    {
        return initialised;
    }
    
    @Override
    public void init(Spatial spatial)
    {
        DrawableUI sun = DrawableManager.getInstance().getObjectWithName("sun");
        Spatial sunSpatial = sun.getDrawable();
        Vector3f sunPosition = sunSpatial.getLocalTranslation();
        Vector3f spatialTargetPosition = sunPosition.add(0,0,200000);
        spatial.setLocalTranslation(spatialTargetPosition);
        spatial.lookAt(sunPosition, new Vector3f(0,1,0));
        
        initialised = true;
    }
    
    @Override
    public void moveSpatial(Spatial spatial)
    {
        
    }
}

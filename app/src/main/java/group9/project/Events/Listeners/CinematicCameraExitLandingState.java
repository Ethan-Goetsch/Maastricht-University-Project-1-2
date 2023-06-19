package group9.project.Events.Listeners;

import com.jme3.scene.Spatial;
import group9.project.Events.IEventListener;
import group9.project.UI.Camera.CinematicCameraControl;
import group9.project.UI.Camera.ICameraControlState;

public class CinematicCameraExitLandingState implements IEventListener, ICameraControlState {
    
    private boolean initialised = false;
    
    public CinematicCameraExitLandingState()
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
        initialised = true;
    }
    
    @Override
    public void moveSpatial(Spatial spatial)
    {
        
    }
}

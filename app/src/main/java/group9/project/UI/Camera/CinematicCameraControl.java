package group9.project.UI.Camera;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class CinematicCameraControl extends AbstractControl {
    
    private Camera cam;
    
    private boolean paused = false;
    
    private Vector3f initialUpVec;
    
    private static ICameraControlState controlState = new CinematicCameraInitialState();
  
    /**
     * 
     * @param cam the camera to control
     */
    public CinematicCameraControl(Camera cam)
    {
        setCamera(cam);
        
        cam.setFrustumPerspective(60, (float)cam.getWidth()/(float)cam.getHeight(), cam.getFrustumNear(), cam.getFrustumFar());
    }
    

    private void setCamera(Camera cam)
    {
        this.cam = cam;
        
        initialUpVec = cam.getUp().clone(); // i sometimes don't know what is up and what is down. i am lost in the abyss, awaiting guidance.
    }

    /**
     * Enables/disabled the camera control.
     * If disabled, this controller will not move the camera.
     * @param enabled 
     */
    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial)
    {
        final CinematicCameraControl control = new CinematicCameraControl(cam);
        
        control.setSpatial(spatial);
        
        return control;
    }
    
    @Override
    protected void controlUpdate(float tpf)
    {
        if (!enabled || paused) return;
        
        if (!CinematicCameraControl.controlState.isInitialised())
        {
            CinematicCameraControl.controlState.init(spatial);
        }
        
        CinematicCameraControl.controlState.moveSpatial(spatial); // let the control state do the work
        
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
        
    }
    
    /**
     * Sets the current state of the controller.
     * @param controlState the state to set the controller to
     */
    public static void setState(ICameraControlState controlState)
    {
        CinematicCameraControl.controlState = controlState; 
    }
    
    /**
     * Pauses/unpauses the camera control.
     * @param paused 
     */
    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }
    
}

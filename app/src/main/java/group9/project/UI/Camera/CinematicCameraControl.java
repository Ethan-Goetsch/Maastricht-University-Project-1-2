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
  
    
    public CinematicCameraControl(Camera cam)
    {
        setCamera(cam);
        
        cam.setFrustumPerspective(60, (float)cam.getWidth()/(float)cam.getHeight(), cam.getFrustumNear(), cam.getFrustumFar());
    }
    
    private void setCamera(Camera cam)
    {
        this.cam = cam;
        
        initialUpVec = cam.getUp().clone();
    }
    
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
        
        CinematicCameraControl.controlState.moveSpatial(spatial);
        
    }
    
    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
        
    }
    
    public static void setState(ICameraControlState controlState)
    {
        CinematicCameraControl.controlState = controlState; 
    }
    
    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }
    
}

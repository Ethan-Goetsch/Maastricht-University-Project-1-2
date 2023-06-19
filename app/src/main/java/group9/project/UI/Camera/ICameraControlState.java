package group9.project.UI.Camera;

import com.jme3.scene.Spatial;

public interface ICameraControlState {
    
    public boolean isInitialised();
    
    public void init(Spatial spatial);
    
    public void moveSpatial(Spatial spatial);
}

package group9.project.UI.Camera;

import com.jme3.scene.Spatial;

public interface ICameraControlState {
    
    public boolean isInitialised();
    
    /**
     * This should be called once, prior to calling the {@code moveSpatial} method.
     * @param spatial 
     */
    public void init(Spatial spatial);
    
    /**
     * Moves the spatial once.
     * @param spatial the spatial to move.
     */
    public void moveSpatial(Spatial spatial);
}


package group9.project.UI.Camera;


public class CinematicModeController {
    
    CinematicCameraControl camControl;
    
    private boolean enabled = true;

    //#region Singleton
    private static CinematicModeController instance = null;
    
    public static CinematicModeController getInstance()
    {
        if (instance == null) instance = new CinematicModeController();
        return instance;
    }
    //#endregion
    
    private CinematicModeController()
    {
        
    }
    
    /**
     * Sets the camera controller.
     * @param camControl 
     */
    public void setCamControl(CinematicCameraControl camControl)
    {
        this.camControl = camControl;
    }
    
    public boolean isCamControlEnabled()
    {
        return enabled;
    }
    
    public void setCamControlEnabled(boolean enabled)
    {
        this.enabled = enabled;
        camControl.setEnabled(enabled);
    }
   
}

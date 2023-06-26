package group9.project.UI.Camera;

public class FreeModeController {
    
    CustomCameraControl camControl;
    
    private boolean enabled = false;

    //#region Singleton
    private static FreeModeController instance = null;
    
    public static FreeModeController getInstance()
    {
        if (instance == null) instance = new FreeModeController();
        return instance;
    }
    //#endregion
    
    private FreeModeController()
    {
        
    }
    
    /**
     * Sets the camera controller.
     * @param camControl 
     */
    public void setCamControl(CustomCameraControl camControl)
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

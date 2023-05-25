package group9.project.UI.Camera;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import group9.project.UI.Input.InputAction;

public class CustomCameraControl extends AbstractControl {
    
    InputManager inputManager;
    Camera cam;
    
    Vector3f initialUpVec; // this is an important variable when rotating the camera horizontally
    
    private boolean enabled;
    
    float delta = 0;
    float speed = 0;
    
    float rotationSpeed = 5;
    float speedSensitivity = 4f;
    
    boolean freeRotation = false;
    
    // used for free camera rotation
    private Vector3f spatialDirection;
    private Quaternion spatialRotation;
        
    public CustomCameraControl(Camera cam)
    {
        setCamera(cam);
    }
    
    /**
     * 
     * @return the speed of the camera
     */
    public float getSpeed()
    {
        return speed;
    }
    
    /**
     * Changes the speed of the camera according to an exponential function.
     * @param increase true if speed should increase, false if speed should decrease
     * @param tpf 
     * @return 
     */
    private float changeSpeed(boolean increase, float tpf)
    {
        if (increase)
        {
            delta += 10f * speedSensitivity * tpf;
        } else 
        {
            delta -= 10f * speedSensitivity * tpf;
        }
        
        return Math.signum(delta) * (float)Math.pow(1.1, Math.abs(delta))-1;
    
    }
    
    /**
     * Sets the local camera object to the argument camera, and saves the up vector of the camera.
     * @param cam 
     */
    private void setCamera(Camera cam)
    {
        this.cam = cam;
        initialUpVec = cam.getUp().clone();
    }
    
    /**
     * Enables/disables the camera control.
     * If disabled, the camera will not be able to move or rotate.
     * @param enabled 
     */
    @Override
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    
    /**
     * Sets the camera to "free rotation" mode.
     * In this mode, the camera can freely "look around" without affecting the motion of the camera.
     * When free rotation mode is disabled, the camera will snap back to point in the direction of motion (i.e. the direction the camera was facing before free rotation mode was enabled).
     * @param freeRotation true to enable free rotation mode, false to disable it
     */
    public void setFreeRotation(boolean freeRotation)
    {
        this.freeRotation = freeRotation;
        if (freeRotation)
        {
            spatialRotation = spatial.getLocalRotation().clone(); // save the current rotation of the spatial
        } else {
            
            spatial.setLocalRotation(spatialRotation); // set rotation of spatial back to saved rotation
        }
    }
    
    /**
     * Registers keybinds (input mappings) with the input manager.
     * 
     * @param inputManager the applications input manager
     */
    public void setInput(InputManager inputManager)
    {
        this.inputManager = inputManager;
        inputManager.setCursorVisible(false);
        
        inputManager.addMapping("Increase Move Speed", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Decrease Move Speed", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Toggle Move", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addMapping("Rotate Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("Rotate Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("Rotate Up", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("Rotate Down", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        
        inputManager.addMapping(InputAction.DETACH_CAMERA_ROTATION, new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        
        AnalogListener analogListener = new AnalogListener() {
            @Override
            public void onAnalog(String name, float value, float tpf) {
                if (!enabled) return;
                if (name.equals("Increase Move Speed"))
                {
                    speed = changeSpeed(true, tpf);
                } else if (name.equals("Decrease Move Speed"))
                {
                    speed = changeSpeed(false, tpf);
                } else if (name.equals("Toggle Move"))
                {
                    enabled = !enabled;
                } else if (name.equals("Rotate Left"))
                {
                    rotateCamera(value, initialUpVec);
                } else if (name.equals("Rotate Right"))
                {
                    rotateCamera(-value, initialUpVec);
                } else if (name.equals("Rotate Up"))
                {
                    rotateCamera(value, new Vector3f(1f,0f,0f));
                } else if (name.equals("Rotate Down"))
                {
                    rotateCamera(-value, new Vector3f(1f,0f,0f));
                } else if (name.equals(InputAction.DETACH_CAMERA_ROTATION))
                {
                    
                }
            }
        };
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (!enabled) return;
                if (name.equals(InputAction.DETACH_CAMERA_ROTATION))
                {
                    if (isPressed)
                    {
                        setFreeRotation(true);
                        System.out.println("hi");
                    } else
                    {
                        setFreeRotation(false);
                    }
                } 
            }
        };
        
        inputManager.addListener(analogListener, new String[]{"Increase Move Speed","Decrease Move Speed","Toggle Move","Rotate Left","Rotate Right","Rotate Up","Rotate Down"});
        inputManager.addListener(actionListener, new String[]{InputAction.DETACH_CAMERA_ROTATION});
    }
    
    /**
     * Rotate the camera by the specified amount around the specified axis.
     *
     * @param value rotation amount
     * @param axis direction of rotation (a unit vector)
     */
    protected void rotateCamera(float value, Vector3f axis) {
        if (!enabled) return;
  
        Quaternion localRotation = this.getSpatial().getLocalRotation();
        Quaternion rotation = new Quaternion();
        if (axis.getX() == 1f) 
        {
            // rotate around x axis
            localRotation.mult(rotation.fromAngles(value*rotationSpeed,0,0));
        } else if (axis.getY() == 1f)
        {
            // rotate around y axis
            localRotation.mult(rotation.fromAngles(0,value*rotationSpeed,0));
        }
        
        this.getSpatial().rotate(rotation);
        
        if (!freeRotation)
        {
            spatialDirection = cam.getDirection().normalize(); // only set direction of spatial to camera direction if not in free rotation mode
        }
        
    }
    
    @Override
    public Control cloneForSpatial(Spatial spatial)
    {
        final CustomCameraControl control = new CustomCameraControl(cam);
        control.setInput(inputManager);
        control.setSpatial(spatial);
        return control;
    }
    
    @Override
    protected void controlUpdate(float tpf)
    {
        if (!enabled) return;
        this.getSpatial().move(spatialDirection.mult(speed).mult(tpf));
    }
    
    @Override
    public void setSpatial(Spatial spatial)
    {
        super.setSpatial(spatial);
        
        spatialDirection = cam.getDirection().normalize();
        spatialRotation = spatial.getLocalRotation();
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

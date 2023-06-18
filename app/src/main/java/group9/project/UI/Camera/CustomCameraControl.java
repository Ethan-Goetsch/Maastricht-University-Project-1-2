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
import group9.project.UI.Input.IInputListener;
import group9.project.UI.Input.KeybindingManager;

public class CustomCameraControl extends AbstractControl implements IInputListener
{
    private final InputManager inputManager;
    private Camera cam;
    
    private Vector3f initialUpVec; // this is an important variable when rotating the camera horizontally
    
    private boolean enabled;
    
    private float delta = 0;
    private float speed = 0;
    
    private final float rotationSpeed = 5;
    private final float speedSensitivity = 4f;
    
    private boolean freeRotation = false;
        
    // constants for registering inputs
    private final String INCREASE_SPEED = "Increase Speed";
    private final String DECREASE_SPEED = "Decrease Speed";
    private final String TOGGLE_MOVE = "Toggle Camera Movement";
    private final String DETACH_CAMERA_ROTATION = "Free Camera Rotation";
    private final String ROTATE_LEFT = "Rotate left";
    private final String ROTATE_RIGHT = "Rotate right";
    private final String ROTATE_UP = "Rotate up";
    private final String ROTATE_DOWN = "Rotate down";
    
    // used for free camera rotation
    private Vector3f spatialDirection;

    private Quaternion spatialRotation;
        
    public CustomCameraControl(Camera cam, InputManager inputManager)
    {
        setCamera(cam);
        this.inputManager = inputManager;
        KeybindingManager.registerListener(this);
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
        }
        else 
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
        }
        else
        {
            spatial.setLocalRotation(spatialRotation); // set rotation of spatial back to saved rotation
        }
    }
    
    public void setDefaultInputs()
    {
        KeybindingManager.setDefaultKeyMapping(INCREASE_SPEED, KeyInput.KEY_W);
        KeybindingManager.setDefaultKeyMapping(DECREASE_SPEED, KeyInput.KEY_S);
        KeybindingManager.setDefaultKeyMapping(TOGGLE_MOVE, KeyInput.KEY_SPACE);
    }
    
    /**
     * Registers keybinds (input mappings) with the input manager.
     * 
     */
    public void setInput()
    {   
        inputManager.addMapping(INCREASE_SPEED, new KeyTrigger(KeybindingManager.getKeyMapping(INCREASE_SPEED)));

        inputManager.addMapping(DECREASE_SPEED, new KeyTrigger(KeybindingManager.getKeyMapping(DECREASE_SPEED)));

        inputManager.addMapping(TOGGLE_MOVE, new KeyTrigger(KeybindingManager.getKeyMapping(TOGGLE_MOVE)));

        inputManager.addMapping(ROTATE_LEFT, new MouseAxisTrigger(MouseInput.AXIS_X, true));

        inputManager.addMapping(ROTATE_RIGHT, new MouseAxisTrigger(MouseInput.AXIS_X, false));

        inputManager.addMapping(ROTATE_UP, new MouseAxisTrigger(MouseInput.AXIS_Y, true));

        inputManager.addMapping(ROTATE_DOWN, new MouseAxisTrigger(MouseInput.AXIS_Y, false));

        
        inputManager.addMapping(DETACH_CAMERA_ROTATION, new MouseButtonTrigger(MouseInput.BUTTON_MIDDLE));
        
       
        
        inputManager.addListener(analogListener, new String[]{INCREASE_SPEED,DECREASE_SPEED,ROTATE_LEFT,ROTATE_RIGHT,ROTATE_UP,ROTATE_DOWN});

        inputManager.addListener(actionListener, new String[]{TOGGLE_MOVE,DETACH_CAMERA_ROTATION});
    }
    
    /**
     * Deletes key mappings from the input manager which can be changed through {@code KeybindingManager}.
     */
    private void deleteMappings()
    {
        inputManager.deleteMapping(INCREASE_SPEED);
        inputManager.deleteMapping(DECREASE_SPEED);
        inputManager.deleteMapping(TOGGLE_MOVE);
    }
    
    /**
     * Rotate the camera by the specified amount around the specified axis.
     *
     * @param value rotation amount
     * @param axis direction of rotation (a unit vector)
     */
    protected void rotateCamera(float value, Vector3f axis)
    {
        if (!enabled)
        {
            return;
        }
  
        Quaternion localRotation = this.getSpatial().getLocalRotation();

        Quaternion rotation = new Quaternion();

        if (axis.getX() == 1f) 
        {
            // rotate around x axis
            localRotation.mult(rotation.fromAngles(value*rotationSpeed,0,0));
        }
        else if (axis.getY() == 1f)
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
        final CustomCameraControl control = new CustomCameraControl(cam, inputManager);

        control.setInput();

        control.setSpatial(spatial);

        return control;
    }
    
    @Override
    protected void controlUpdate(float tpf)
    {
        if (!enabled)
        {
            return;
        }

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
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }  
    
    @Override
    public void onInputChange()
    {
        deleteMappings();
        inputManager.removeListener(analogListener);
        inputManager.removeListener(actionListener);
        setInput();
    }
    
    private AnalogListener analogListener = (String name, float value, float tpf) -> {
       
        
            if (!enabled)
            {
                return;
            }
            
            switch (name) {
                case INCREASE_SPEED:
                    speed = changeSpeed(true, tpf);
                    break;
                case DECREASE_SPEED:
                    speed = changeSpeed(false, tpf);
                    break;
                case ROTATE_LEFT:
                    rotateCamera(value, initialUpVec);
                    break;
                case ROTATE_RIGHT:
                    rotateCamera(-value, initialUpVec);
                    break;
                case ROTATE_UP:
                    rotateCamera(value, new Vector3f(1f,0f,0f));
                    break;
                case ROTATE_DOWN:
                    rotateCamera(-value, new Vector3f(1f,0f,0f));
                    break;
                case DETACH_CAMERA_ROTATION:
                    break;
                default:
                    break;
            }
        };
    
    private ActionListener actionListener = (String name, boolean isPressed, float tpf) -> {
        
            if (isPressed && name.equals(TOGGLE_MOVE))
            {
                enabled = !enabled;
            }
            
            if (!enabled)
            {
                return;
            }
            
            if (name.equals(DETACH_CAMERA_ROTATION))
            {
                if (isPressed)
                {
                    setFreeRotation(true);
                }
                else
                {
                    setFreeRotation(false);
                } 
            }
        };
}
package group9.project.UI.Camera;

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableUI;
import group9.project.UI.Input.IInputListener;
import group9.project.UI.Input.KeybindingManager;

public class ViewSwitcher implements ActionListener, IInputListener
{
    private Camera cam;

    private InputManager inputManager;
    
    private boolean enabled = true;
    
    // seperate chase cameras for each celestial body (and rocket):
    private ChaseCamera sunCam;

    private ChaseCamera mercuryCam;

    private ChaseCamera venusCam; 

    private ChaseCamera earthCam;

    private ChaseCamera marsCam;

    private ChaseCamera jupiterCam;

    private ChaseCamera saturnCam;

    private ChaseCamera titanCam;

    private ChaseCamera neptuneCam;

    private ChaseCamera uranusCam;

    private ChaseCamera rocketCam;

    private ChaseCamera moonCam;
    
    // array of chase cams for ease of looping:
    private ChaseCamera[] chaseCams = new ChaseCamera[]
    {
        sunCam,
        mercuryCam,
        venusCam,
        earthCam,
        marsCam,
        jupiterCam,
        saturnCam,
        titanCam,
        neptuneCam,
        uranusCam,
        rocketCam,
        moonCam
    };
        
    
    private ChaseCamera prevCam;

    private CustomCameraControl camControl;

    private CameraNode camNode;
    

    // string constants for registering inputs:
    private final String sun = "Follow Sun";

    private final String mercury = "Follow Mercury";

    private final String venus = "Follow Venus";

    private final String earth = "Follow Earth";

    private final String mars = "Follow Mars";

    private final String jupiter = "Follow Jupiter";

    private final String saturn = "Follow Saturn";

    private final String titan = "Follow Titan";

    private final String neptune = "Follow Neptune";

    private final String uranus = "Follow Uranus";

    private final String rocket = "Follow Rocket";

    private final String moon = "Follow Moon";
    
    private String[] mappingNames = new String[]
        {
            sun,
            mercury,
            venus,
            earth,
            mars,
            jupiter,
            saturn,
            titan,
            neptune,
            uranus,
            rocket,
            moon
        };
    
    /**
     * Constructor.
     * @param cam The application's camera
     * @param inputManager the application's input manager
     * @param camControl the control for the camera node
     * @param camNode the camera node
     */
    public ViewSwitcher(Camera cam, InputManager inputManager, CustomCameraControl camControl, CameraNode camNode)
    {
        this.cam = cam;

        this.inputManager = inputManager;

        this.camControl = camControl;

        this.camNode = camNode;
        
        prevCam = null;
        
        KeybindingManager.registerListener(this);

        initCams();

        //setDefaultInputs();
        registerInputs();
    }
    
    /**
     * Instantiates and configures the chase cameras to follow their target.
     */
    private void initCams()
    {
       
        String[] camTargets = new String[]
        {
            "sun",
            "mercury",
            "venus",
            "earth",
            "mars",
            "jupiter",
            "saturn",
            "titan",
            "neptune",
            "uranus",
            "rocket",
            "moon"
        };
        
        for (int i = 0; i < chaseCams.length; i++)
        {
            DrawableUI target = DrawableManager.getInstance().getObjectWithName(camTargets[i]);
            
            chaseCams[i] = new ChaseCamera(cam, target.getDrawable(), inputManager);
            
            chaseCams[i].setEnabled(false); // should initially be disabled (otherwise we have 12 enabled chase cameras)
            

            // viewing distance:
            chaseCams[i].setDefaultDistance(target.getPreferredViewDistance());

            chaseCams[i].setMaxDistance(target.getPreferredViewDistance()*5);
            

            // some other stuff which makes the camera behave better:
            chaseCams[i].setDefaultVerticalRotation((float)(-Math.PI/4));

            chaseCams[i].setZoomSensitivity(target.getPreferredViewDistance()/10);

            chaseCams[i].setMaxVerticalRotation((float)Math.PI/2);

            chaseCams[i].setMinVerticalRotation((float)(-Math.PI/3));

            chaseCams[i].setSmoothMotion(false);

            chaseCams[i].setTrailingSensitivity(100f);

            chaseCams[i].setDragToRotate(false);
        }

    }
    
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        if (prevCam != null)
        {
            prevCam.setEnabled(enabled);
        }
    }
    
    /**
     * Sets the default keybindings for switching the planets
     */
    private void setDefaultInputs()
    {
        KeybindingManager.setDefaultKeyMapping(sun, KeyInput.KEY_1);
        KeybindingManager.setDefaultKeyMapping(mercury, KeyInput.KEY_2);
        KeybindingManager.setDefaultKeyMapping(venus, KeyInput.KEY_3);
        KeybindingManager.setDefaultKeyMapping(earth, KeyInput.KEY_4);
        KeybindingManager.setDefaultKeyMapping(mars, KeyInput.KEY_5);
        KeybindingManager.setDefaultKeyMapping(jupiter, KeyInput.KEY_6);
        KeybindingManager.setDefaultKeyMapping(saturn, KeyInput.KEY_7);
        KeybindingManager.setDefaultKeyMapping(titan, KeyInput.KEY_0);
        KeybindingManager.setDefaultKeyMapping(neptune, KeyInput.KEY_8);
        KeybindingManager.setDefaultKeyMapping(uranus, KeyInput.KEY_9);
        KeybindingManager.setDefaultKeyMapping(rocket, KeyInput.KEY_MINUS);
        KeybindingManager.setDefaultKeyMapping(moon, KeyInput.KEY_EQUALS); 
    }
    
    /**
     * Maps keys (1-9,-,=) to specific chase cams.
     */
    private void registerInputs()
    {   
        System.out.println("registed inputs");
        System.out.println(KeybindingManager.getKeyMapping(titan));
        inputManager.addMapping(sun, new KeyTrigger(KeybindingManager.getKeyMapping(sun)));

        inputManager.addMapping(mercury, new KeyTrigger(KeybindingManager.getKeyMapping(mercury)));
        
        inputManager.addMapping(venus, new KeyTrigger(KeybindingManager.getKeyMapping(venus)));

        inputManager.addMapping(earth, new KeyTrigger(KeybindingManager.getKeyMapping(earth)));

        inputManager.addMapping(mars, new KeyTrigger(KeybindingManager.getKeyMapping(mars)));

        inputManager.addMapping(jupiter, new KeyTrigger(KeybindingManager.getKeyMapping(jupiter)));

        inputManager.addMapping(saturn, new KeyTrigger(KeybindingManager.getKeyMapping(saturn)));

        inputManager.addMapping(titan, new KeyTrigger(KeybindingManager.getKeyMapping(titan)));

        inputManager.addMapping(neptune, new KeyTrigger(KeybindingManager.getKeyMapping(neptune)));

        inputManager.addMapping(uranus, new KeyTrigger(KeybindingManager.getKeyMapping(uranus)));

        inputManager.addMapping(rocket, new KeyTrigger(KeybindingManager.getKeyMapping(rocket)));

        inputManager.addMapping(moon, new KeyTrigger(KeybindingManager.getKeyMapping(moon)));
        
        
        
        inputManager.addListener(this, mappingNames);
    }
    
    /**
     * Registers input mappings with the input manager.
     * Should be called when the user changes a keybinding.
     */
    public void registerNewInputs()
    {
        deleteMappings();
        inputManager.removeListener(this);
        registerInputs();
    }
    
    private void deleteMappings()
    {
        for (String mappingName : mappingNames) {
            inputManager.deleteMapping(mappingName);
        }
    }
    
    /**
     * Handles the logic responsible for switching the camera view to a different chase camera.
     * @param chaseCam the view will be switched to this chase camera
     */
    public void switchView(ChaseCamera chaseCam)
    {
        if (!enabled)
        {
            return;
        }
        
        inputManager.setCursorVisible(false);

        if (prevCam != null)
        {
            if (prevCam.equals(chaseCam)) // if the previous chase cam is the same as the one that we are trying to switch to, then exit the chase cam
            {
                chaseCam.setEnabled(false);

                camControl.setEnabled(true);

                camNode.setEnabled(true);
                
                cam.update();
                    
                prevCam = null;

                return;
            }
            else // is previous cam was a different chase cam, then we can just disable it.
            {
                prevCam.setEnabled(false); 
            }
        }

        // switch to the desired chase camera:
        prevCam = chaseCam;   

        chaseCam.setEnabled(true);

        camControl.setEnabled(false)
        ;
        camNode.setEnabled(false);
        
        cam.update();

        inputManager.setCursorVisible(false);            
    }
    

    @Override
    public void onAction(String name, boolean isPressed, float tpf)
    {
        if (isPressed)
        {
            System.out.println("pressed " + name);
          switch (name)
          {
            case sun:
                switchView(chaseCams[0]);
                break;
            case mercury:
                switchView(chaseCams[1]);
                break;
            case venus:
                switchView(chaseCams[2]);
                break;
            case earth:
                switchView(chaseCams[3]);
                break;
            case mars:
                switchView(chaseCams[4]);
                break;
            case jupiter:
                switchView(chaseCams[5]);
                break;
            case saturn:
                switchView(chaseCams[6]);
                break;
            case titan:
                switchView(chaseCams[7]);
                break;
            case neptune:
                switchView(chaseCams[8]);
                break;
            case uranus:
                switchView(chaseCams[9]);
                break;
            case rocket:
                switchView(chaseCams[10]);
                break;
            case moon:
                switchView(chaseCams[11]);
                break;
            default:
                break;
           }
        }
    }
    
    @Override
    public void onInputChange()
    {
        registerNewInputs();
    }
}

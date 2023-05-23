package group9.project.UI.Camera;

import com.jme3.app.Application;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.scene.CameraNode;
import group9.project.UI.Drawable.DrawableManager;
import group9.project.UI.Drawable.DrawableUI;

public class ViewSwitcher implements ActionListener{
    private Application app;
    private InputManager inputManager;
    
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
    ChaseCamera[] chaseCams = new ChaseCamera[]{
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
    
    public ViewSwitcher(Application app, InputManager inputManager, CustomCameraControl camControl, CameraNode camNode)
    {
        this.app = app;
        this.inputManager = inputManager;
        this.camControl = camControl;
        this.camNode = camNode;
        
        prevCam = null;
        
        initCams();
        registerInputs();
        
    }
    
    /**
     * Instantiates and configures the chase cameras to follow their target.
     */
    private void initCams()
    {
       
        String[] camTargets = new String[]{
            "sun",
            "mercury",
            "venus",
            "earth",
            "mars",
            "jupiter",
            "saturn",
            "neptune",
            "titan",
            "uranus",
            "rocket",
            "moon"
        };
        
        for (int i = 0; i < chaseCams.length; i++) {
            DrawableUI target = DrawableManager.getInstance().getObjectWithName(camTargets[i]);
            
            chaseCams[i] = new ChaseCamera(app.getCamera(), target.getDrawable(), inputManager);
            
            chaseCams[i].setEnabled(false); // should initially be disabled (otherwise we have 12 enabled chase cameras)
            
            //viewing distance:
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
    
    /**
     * Maps keys (1-9,-,=) to specific chase cams.
     */
    private void registerInputs()
    {
        inputManager.addMapping(sun, new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping(mercury, new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping(venus, new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping(earth, new KeyTrigger(KeyInput.KEY_4));
        inputManager.addMapping(mars, new KeyTrigger(KeyInput.KEY_5));
        inputManager.addMapping(jupiter, new KeyTrigger(KeyInput.KEY_6));
        inputManager.addMapping(saturn, new KeyTrigger(KeyInput.KEY_7));
        inputManager.addMapping(titan, new KeyTrigger(KeyInput.KEY_0));
        inputManager.addMapping(neptune, new KeyTrigger(KeyInput.KEY_8));
        inputManager.addMapping(uranus, new KeyTrigger(KeyInput.KEY_9));
        inputManager.addMapping(rocket, new KeyTrigger(KeyInput.KEY_MINUS));
        inputManager.addMapping(moon, new KeyTrigger(KeyInput.KEY_EQUALS));
        
        String[] inputs = new String[]{
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
        
        inputManager.addListener(this, inputs);
    }
    
    /**
     * Handles the logic responsible for switching the camera view to a different chase camera.
     * @param chaseCam 
     */
    public void switchView(ChaseCamera chaseCam)
    {
        inputManager.setCursorVisible(false);
        if (prevCam != null)
        {
            if (prevCam.equals(chaseCam)) // if the previous chase cam is the same as the one that we are trying to switch to, then exit the chase cam
            {
                
                chaseCam.setEnabled(false);
                camControl.setEnabled(true);
                camNode.setEnabled(true);
                
                app.getCamera().update();
                    
                prevCam = null;
                return;
            } else // is previous cam was a different chase cam, then we can just disable it.
            {
                prevCam.setEnabled(false); 
            }
        }

        // switch to the desired chase camera:
        prevCam = chaseCam;   
        chaseCam.setEnabled(true);
        camControl.setEnabled(false);
        camNode.setEnabled(false);
        
        app.getCamera().update();
        inputManager.setCursorVisible(false);            
    }
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf)
    {
        if (isPressed)
        {
          switch (name) {
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
    
}

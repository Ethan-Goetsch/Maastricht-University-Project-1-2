package group9.project.UI.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import group9.project.MissionControl;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.Camera.CustomCameraControl;
import group9.project.Utility.Date.DateCalculator;

public class SystemInformationView implements IHudDrawable{
    
    private Node rootNode; // this is the root node of the SystemInformationView's local scene graph instance
    
    private boolean enabled;
    
    private BitmapFont font;
    
    private Camera cam;
    
    private BitmapText speedLabel, dateLabel, distanceLabel, rocketSpeedLabel, positionLabel;
    
    private CustomCameraControl camControl;

    
    /**
     * Creates an instance of {@code SystemInformationView}.
     * 
     * @param cam the application's camera instance, used to display the camera's position
     * @param font the font used for the labels
     * @param camControl the camera control, used to display the speed of the camera
     */
    public SystemInformationView(Camera cam, BitmapFont font, CustomCameraControl camControl)
    {
        rootNode = new Node();
        enabled = true;
        this.font = font;
        this.camControl = camControl;
        this.cam = cam;
        init();
    }
    
    private void init()
    {
        // instantiate and configure labels:

        this.dateLabel = new BitmapText(font);
        this.dateLabel.setLocalTranslation(MissionControl.getWidth()-500, MissionControl.getHeight()-50, 0);
        
        this.distanceLabel = new BitmapText(font);
        this.distanceLabel.setLocalTranslation(MissionControl.getWidth()-500, MissionControl.getHeight()-100, 0);
        
        this.rocketSpeedLabel = new BitmapText(font);
        this.rocketSpeedLabel.setLocalTranslation(MissionControl.getWidth()-500, MissionControl.getHeight()-150, 0);
        
        this.positionLabel = new BitmapText(font);
        this.positionLabel.setLocalTranslation(MissionControl.getWidth()-500, MissionControl.getHeight()-200, 0);
        
        speedLabel = new BitmapText(font);
        speedLabel.setLocalTranslation(MissionControl.getWidth()/2, MissionControl.getHeight()-100, 0);
        
        attachChildren();
    }

    /**
     * Attaches the labels to the local scene graph
     */
    private void attachChildren()
    {
        rootNode.attachChild(dateLabel);
        rootNode.attachChild(distanceLabel);
        rootNode.attachChild(dateLabel);
        rootNode.attachChild(rocketSpeedLabel);
        rootNode.attachChild(speedLabel);
    }

    /**
     * Removes the labels from the local scene graph
     */
    private void detachChildren()
    {
        rootNode.detachChild(dateLabel);
        rootNode.detachChild(distanceLabel);
        rootNode.detachChild(dateLabel);
        rootNode.detachChild(rocketSpeedLabel);
        rootNode.detachChild(speedLabel);
    }
    
    @Override
    public void update()
    {
        if (!enabled) return;

        speedLabel.setText(camControl.getSpeed() + "");
        dateLabel.setText("Date: " + DateCalculator.getInstance().getCurrentDate() + "");
        rocketSpeedLabel.setText("Rocket speed: " + PhysicsObjectData.getInstance().getRocketShipSpeed() + " km/s");
        distanceLabel.setText("Distance to titan: " + (float)((int)(PhysicsObjectData.getInstance().getRocketShipDistanceToTitan()*100000))/100000.0 + " km");
        positionLabel.setText(cam.getLocation().toString());
    }

    @Override
    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
        {
           attachChildren(); 
        } else  {
            detachChildren();
        }
    }
    
}

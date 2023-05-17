/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import group9.project.MissionControl;
import group9.project.Physics.Managers.PhysicsObjectData;
import group9.project.UI.Camera.CustomCameraControl;
import group9.project.Utility.Date.DateCalculator;

/**
 *
 * @author natem
 */
public class SystemInformationView implements IHudDrawable{
    
    private Node rootNode;
    
    private boolean enabled;
    
    private BitmapFont font;
    
    private Camera cam;
    
    private BitmapText speedLabel, dateLabel, distanceLabel, rocketSpeedLabel, positionLabel;
    
    private CustomCameraControl camControl;

    
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
        this.dateLabel = new BitmapText(font);
        this.dateLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-50, 0);
        
        this.distanceLabel = new BitmapText(font);
        this.distanceLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-100, 0);
        
        this.rocketSpeedLabel = new BitmapText(font);
        this.rocketSpeedLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-150, 0);
        
        this.positionLabel = new BitmapText(font);
        this.positionLabel.setLocalTranslation(MissionControl.WIDTH-500, MissionControl.HEIGHT-200, 0);
        
        speedLabel = new BitmapText(font);
        speedLabel.setLocalTranslation(MissionControl.WIDTH/2, MissionControl.HEIGHT-100, 0);
        
        rootNode.attachChild(dateLabel);
        rootNode.attachChild(distanceLabel);
        rootNode.attachChild(dateLabel);
        rootNode.attachChild(rocketSpeedLabel);
        rootNode.attachChild(speedLabel);
    }
    
    @Override
    public void update()
    {
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
    }
    
    
    
    
}

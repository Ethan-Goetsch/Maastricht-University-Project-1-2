/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group9.project.UI;

import com.jme3.font.BitmapFont;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.DefaultRangedValueModel;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.Slider;
import com.simsilica.lemur.component.BorderLayout;
import com.simsilica.lemur.core.GuiLayout;
import com.simsilica.lemur.style.BaseStyles;
import group9.project.MissionControl;
import group9.project.UI.Input.InputAction;

/**
 *
 * @author natem
 */
public class Menu implements ActionListener{
 
    Node guiNode;
    BitmapFont font;
    boolean enabled;
    
    Button quitButton;
    Container myWindow;
    
    private final float FONT_SIZE = 56;
    
    Vector3f componentSize = new Vector3f(MissionControl.WIDTH/4, MissionControl.HEIGHT/16,0);
    
    public Menu(Node guiNode, BitmapFont font)
    {
        this.guiNode = guiNode;
        this.font = font;
        
        init();
    }
    
    public void init()
    {
        enabled = false;
        
        
    
        // Create a simple container for our elements
        myWindow = new Container();
        
        // Put it somewhere that we will see it
        // Note: Lemur GUI elements grow down from the upper left corner.
        myWindow.setLocalTranslation(MissionControl.WIDTH/2 - MissionControl.WIDTH/8, MissionControl.HEIGHT/2 + MissionControl.HEIGHT/16, 0);
    
        // Add some elements
        Label menuTitle = myWindow.addChild(new Label("Pause Menu"));
        menuTitle.setFontSize(FONT_SIZE);
        menuTitle.setPreferredSize(componentSize);
        
        /*
        Container sliderContainer = new Container(new BorderLayout());
        DefaultRangedValueModel sliderModel = new DefaultRangedValueModel(0, 10, 5);
        Slider simSpeedSlider = new Slider(sliderModel, Axis.X);
        simSpeedSlider.setPreferredSize(componentSize);
        sliderContainer.addChild(simSpeedSlider, BorderLayout.Position.East);
        //simSpeedSlider.setSize(componentSize);
        Label sliderLabel = sliderContainer.addChild(new Label("Simulation Speed"), BorderLayout.Position.West);
        sliderLabel.setFontSize(FONT_SIZE);
        myWindow.addChild(sliderContainer);
*/
        
        
        Button quitButton = myWindow.addChild(new Button("Quit To Desktop"));
        quitButton.setFontSize(FONT_SIZE);
        quitButton.setPreferredSize(componentSize);
        quitButton.addClickCommands(new Command<Button>() {
                @Override
                public void execute( Button source ) {
                    System.exit(0);
                }
            });          
        
        // create buttons
        quitButton = new Button("Quit");
        System.out.println(MissionControl.getInstance().cameraNode);
    }
    
    public void registerKeys(InputManager inputManager)
    {
        inputManager.addMapping(InputAction.OPEN_MENU, new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addListener(this, new String[]{InputAction.OPEN_MENU});
    }
    
    public void onAction(String name, boolean keyPressed, float tpf)
    {
        if (name.equals(InputAction.OPEN_MENU) && keyPressed)
        {
            setEnabled(!enabled);
        }
    }
    
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
        MissionControl.getInstance().setPaused(enabled); // pause the game
        if (enabled)
        {
            MissionControl.getInstance().setCursorVisible(true);
            guiNode.attachChild(myWindow);
        } else {
            MissionControl.getInstance().setCursorVisible(false);
            guiNode.detachChild(myWindow);
        }
        
    }
    
    
}
